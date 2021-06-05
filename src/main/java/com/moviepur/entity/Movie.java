package com.moviepur.entity;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
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
	
	private IndustriesName industryName;	
	private String runTime;

	private String directors;
	private String writers;
	private String stars;

	private Map<String, Float> rating ;
	private Map<String,String> download_link ;

	
	private Set<String> genre ;
	private Set<String> language ;
	private Set<String> otherImages;

	public Movie() {
		type = Type.Movie;
		
		rating =new LinkedHashMap<>();
		rating.put("Moviepur", null);
		rating.put("IMDb", null);
		rating.put("Rotten_Tomatoes", null);
		
		download_link = new LinkedHashMap<>();
		download_link.put("Download", null);
		
		genre =  new LinkedHashSet<>();
		genre.add("Action");
		
		language = new LinkedHashSet<>();
		language.add("English");
		language.add("Hindi");
		
		otherImages = new LinkedHashSet<>();
		otherImages.add(null);
		
	}
}

