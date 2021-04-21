package com.moviepur.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Movie {
	
	private int id;
	private String name, image_url;
	private String description;
	private LocalDate releaseDate;
	private Type type;

	private Map<String, Float> rating = new LinkedHashMap<>(3,0.1f);

	private Map<String,String> download_link = new LinkedHashMap<>(5, 0.25f);

	private Set<String> genre = new HashSet<>(5,0.5f);

	private Set<String> language = new HashSet<>(2,0.5f);

}

