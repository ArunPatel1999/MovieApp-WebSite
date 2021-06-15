package com.moviepur.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeriesDowload {

	private int partId;
	private String partName;
	private String partLink;
	
}
