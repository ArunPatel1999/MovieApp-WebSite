package com.moviepur.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviepur.entity.Movie;

@Service
public class AdminApiServiceImpl implements AdminApiService {

//	@Value("${Moviepur_Api_Url}")
//	private String MOVIEPURURL;
	
	private final String MOVIEPURURL ="http://127.0.0.1:9090";
	
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Map<String, Object>> getAllList() {
		return restTemplate.exchange(MOVIEPURURL+"/main/getForSite/Moviepur",HttpMethod.GET,null, new ParameterizedTypeReference<List<Map<String, Object>>>() {}).getBody();
	}
	
	@Override
	public List<Map<String, Object>> searchByName(String name)  {
		return restTemplate.exchange(MOVIEPURURL+"/main/getForSite/"+name,HttpMethod.GET,null, new ParameterizedTypeReference<List<Map<String, Object>>>() {}).getBody();
	}
	
	@Override
	public Movie save(Movie movie) {
		return restTemplate.postForEntity(MOVIEPURURL+"/main/add", movie, Movie.class).getBody();
	}

	@Override
	public void update(int id, Movie movie) {
		 restTemplate.put(MOVIEPURURL+"/main/update/"+id, movie);
	}

	@Override
	public Movie getById(int id) {
		return restTemplate.postForObject(MOVIEPURURL+"/main/get/"+id, null, Movie.class);
	}

	
	
}
