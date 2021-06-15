package com.moviepur.entity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Movie {

	private int id;
	private String name, image_url;
	private String description;
	private LocalDate releaseDate;
	private Type type;

	
	private String movieDownload;
	
	
	private IndustriesName industryName;	
	private String runTime;

	private String directors;
	private String writers;
	private String stars;

	private float rottenTomatoes;
	private float imdb;
	private float moviepur;
	
	private Set<SeriesDowload> seriesDowloads;
	private Set<String> genre ;
	private Set<String> language ;
	private Set<String> otherImages;

	public Movie() {
		type = Type.Movie;
		
		genre =  new LinkedHashSet<>();
		genre.add("Action");
		
		language = new LinkedHashSet<>();
		language.add("English");
		language.add("Hindi");
		
		otherImages = new LinkedHashSet<>();
		otherImages.add(null);
	
		seriesDowloads= new LinkedHashSet<>();
		seriesDowloads.add(new SeriesDowload());
		
	}
}

