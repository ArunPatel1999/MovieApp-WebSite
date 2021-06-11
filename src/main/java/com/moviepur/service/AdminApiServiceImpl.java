package com.moviepur.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.moviepur.entity.FirebaseClass;
import com.moviepur.entity.Movie;

@Service
public class AdminApiServiceImpl implements AdminApiService {

//	@Value("${Moviepur_Api_Url}")
//	private String MOVIEPURURL;
	
	private final String MOVIEPURURL ="http://127.0.0.1:9090";

	private FirebaseClass firebaseClass;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Map<String, Object>> getAllList() {
		return restTemplate.exchange(MOVIEPURURL + "/main/getForSite/Moviepur", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
				}).getBody();
	}

	@Override
	public List<Map<String, Object>> searchByName(String name) {
		return restTemplate.exchange(MOVIEPURURL + "/main/getForSite/" + name, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Map<String, Object>>>() {
				}).getBody();
	}

	@Override
	public Movie save(Movie movie) {
		return restTemplate.postForEntity(MOVIEPURURL + "/main/add", movie, Movie.class).getBody();
	}

	@Override
	public void update(int id, Movie movie) {
		restTemplate.put(MOVIEPURURL + "/main/update/" + id, movie);
	}

	@Override
	public Movie getById(int id) {
		return restTemplate.postForObject(MOVIEPURURL + "/main/get/" + id, null, Movie.class);
	}

	
	@Override
	public Movie saveWithFile(Movie movie, MultipartFile image, MultipartFile[] otherImages, MultipartFile[] downloads) {
		movie.setImage_url(uploadingFirebase(""+movie.getType()+"/"+movie.getName()+"/imageUrl/", movie.getName()+image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")), image));
		Set<String> otherImageName = new LinkedHashSet<>();
		int i=1;
		for (MultipartFile  otherImage: otherImages) 
			otherImageName.add(uploadingFirebase(""+movie.getType()+"/"+movie.getName()+"/otherImages/", movie.getName()+"_"+(i++)+""+otherImage.getOriginalFilename().substring(otherImage.getOriginalFilename().lastIndexOf(".")), otherImage));
		movie.setOtherImages(otherImageName);
		
		
		Map<String, String> downlodMap = new LinkedHashMap<>();
		if(downloads.length == 1) 
			downlodMap.put("Download", uploadingFirebase(""+movie.getType()+"/"+movie.getName()+"/video/", movie.getName()+downloads[0].getOriginalFilename().substring(downloads[0].getOriginalFilename().lastIndexOf(".")), downloads[0]));
		
		i=1;
		if(downloads.length >= 1) {
		for (MultipartFile  download: downloads) 
			downlodMap.put( "Part_"+i , uploadingFirebase(""+movie.getType()+"/"+movie.getName()+"/video/", movie.getName()+"_part_"+(i++)+""+download.getOriginalFilename().substring(download.getOriginalFilename().lastIndexOf(".")), download));
		}
		
		movie.setDownload_link(downlodMap);
		
		return restTemplate.postForEntity(MOVIEPURURL + "/main/add", movie, Movie.class).getBody();
	}
	
	private String uploadingFirebase(String folderName, String fileName, MultipartFile multipartFile) {
		try {
			File tempFile = new File(fileName);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(multipartFile.getBytes());
			fos.close();

			BlobId blobId = BlobId.of(firebaseClass.getBuket(), folderName + fileName);

			Map<String, String> map = new HashMap<>();
			map.put("firebaseStorageDownloadTokens", "moviepur");

			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType("media").build();

			Credentials credentials = GoogleCredentials.fromStream(createFirebaseCredential());

			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
			storage.create(blobInfo, Files.readAllBytes(tempFile.toPath()));
			tempFile.delete(); // to delete the copy of uploaded file stored in the project folder

			return "https://firebasestorage.googleapis.com/v0/b/"+firebaseClass.getBuket()+"/o/"
					+ (folderName + fileName).replace("/", "%2F") + "?alt=media&token=moviepur";
		} catch (Exception e) {
			return "error";
		}
	}

	public String firebaseClassUpdate() {
		firebaseClass = restTemplate.getForObject(MOVIEPURURL + "/main/firebase", FirebaseClass.class);
		return "success";
	}

	private InputStream createFirebaseCredential() throws Exception {

		// String privateKey =
		// environment.getRequiredProperty("FIREBASE_PRIVATE_KEY").replace("\\n", "\n");

		if (firebaseClass == null)
			firebaseClassUpdate();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(firebaseClass);

		return IOUtils.toInputStream(jsonString, Charset.defaultCharset());
	}

	

}
