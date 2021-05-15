package com.moviepur.service;

import java.util.List;

import com.moviepur.entity.Movie;
import com.moviepur.entity.MovieLite;
import com.moviepur.entity.Type;

public interface MoviepurApiService {

	public List<MovieLite> getAll();
	
	public List<MovieLite> getAllByType(Type type);
	
	public Movie getById(int id);
	
}
