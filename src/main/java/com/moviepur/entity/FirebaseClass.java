package com.moviepur.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FirebaseClass {

	private int id;
	private String buket;
	private String type;
	private String project_id;
	private String private_key_id;
	private String private_key;
	private String client_email;
	private String client_id;
	private String auth_uri;
	private String token_uri;
	private String auth_provider_x509_cert_url;
	private String client_x509_cert_url;
}
