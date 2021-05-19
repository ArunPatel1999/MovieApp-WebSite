package com.moviepur.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviepur.entity.Movie;
import com.moviepur.entity.MovieLite;
import com.moviepur.entity.Type;
import com.moviepur.service.MoviepurApiService;

@Service
public class MoviepurApiServiceImpl implements MoviepurApiService {

	@Value("${Moviepur_Api_Url}")
	private String MOVIEPURURL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Override
	public List<MovieLite> getAll() {
		ResponseEntity<MovieLite[]> entity = restTemplate.getForEntity(MOVIEPURURL+"/all", MovieLite[].class);
		return Arrays.asList(entity.getBody());
	}


	@Override
	public List<MovieLite> getAllByType(Type type) {
		ResponseEntity<MovieLite[]> entity = restTemplate.getForEntity(MOVIEPURURL+"/"+type, MovieLite[].class);
		return Arrays.asList(entity.getBody());
	}


	@Override
	public Movie getById(int id) {
		return restTemplate.postForObject(MOVIEPURURL+"/main/get/"+id, null, Movie.class);
	}
	
}
