package com.moviepur.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.moviepur.entity.Movie;

public interface AdminApiService {


	public List<Map<String, Object>> getAllList();
	
	public Movie getById(int id);
		
	public Movie save(Movie movie);

	public void update(int id ,Movie movie);

	public List<Map<String, Object>> searchByName(String parameter);

	public String firebaseClassUpdate();
	
	public Movie saveWithFile(Movie	movie,MultipartFile image,MultipartFile[] otherImage,MultipartFile[] download);
}
