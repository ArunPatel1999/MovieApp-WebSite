package com.moviepur.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviepur.entity.Movie;
import com.moviepur.service.LocalTesting;

@Service
public class LocalTestingImpl implements LocalTesting {

	private final String LOCALURL ="http://127.0.0.1:9090";
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Movie> getAllList() {
		return Arrays.asList(restTemplate.getForEntity(LOCALURL+"/main", Movie[].class).getBody());
	}

	@Override
	public Movie save(Movie movie) {
		return restTemplate.postForEntity(LOCALURL+"/main/add", movie, Movie.class).getBody();
	}

	@Override
	public void update(int id, Movie movie) {
		 restTemplate.put(LOCALURL+"/main/update/"+id, movie);
	}

	@Override
	public Movie getById(int id) {
		return restTemplate.postForObject(LOCALURL+"/main/get/"+id, null, Movie.class);
	}
	
	
}
