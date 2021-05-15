package com.moviepur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/login")
	public String getLoginPage() {
		return "Login";
	}
	
	@GetMapping("/add")
	public String getAddPage() {
		return "Add";
	}
	
	@GetMapping
	public String all() {
		return "All";
	}
	
	@GetMapping("/fulldetail")
	public String getFullDetails() {
		return "All";
	}
	
}
