package com.moviepur.service;

import java.util.List;

import com.moviepur.entity.Movie;

public interface LocalTesting {
	
	public List<Movie> getAllList();
	
	public Movie getById(int id);
		
	public Movie save(Movie movie);

	public void update(int id ,Movie movie);

}
