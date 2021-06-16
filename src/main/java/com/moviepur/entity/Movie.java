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
	private String name;
	private String image_url;
	private String description;
	private LocalDate releaseDate;
	private Type type;
	private IndustriesName industryName;
	private String runTime;
	private String directors;
	private String writers;
	private String stars;
	private float rottenTomatoes;
	private float imdb;
	private float moviepur;
	private String movieDownloadLink;
	private Set<SeriesDownloadLink> seriesDownloadLinks;
	private Set<String> genre ;
	private Set<String> language ;
	private Set<String> otherImages;

	public Movie() {
		type = Type.Movie;
		
		seriesDownloadLinks =  new LinkedHashSet<>();
		seriesDownloadLinks.add(new SeriesDownloadLink(1,"a","b","c","d"));
		
		genre =  new LinkedHashSet<>();
		genre.add("Action");
		
		language = new LinkedHashSet<>();
		language.add("English");
		language.add("Hindi");
		
		otherImages = new LinkedHashSet<>();
		
		
	}
}

