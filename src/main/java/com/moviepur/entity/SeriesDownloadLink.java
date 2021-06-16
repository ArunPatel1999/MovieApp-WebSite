package com.moviepur.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDownloadLink {

	private int partId;
	private String partName;
	private String partRunTime;
	private String partImage;
	private String partLink;
}
