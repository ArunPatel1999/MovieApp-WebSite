package com.moviepur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moviepur.service.MoviepurApiService;

@Controller
public class PageController {

	@Autowired
	private MoviepurApiService moviepurApiService;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("MovieList", moviepurApiService.getAll());
		return "All";
	}
	
	@PostMapping("/fullDetails")
	public String getFullDetails(@RequestParam("id") int id,Model model) {
		model.addAttribute("Title","Edit");
		model.addAttribute("Movie",	moviepurApiService.getById(id));
		return "Add";
	}
	
	
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "Login";
	}
	
	@GetMapping("/add")
	public String getAddPage() {
		return "Add";
	}
	
	@GetMapping("/edit")
	public String getEditPage(@PathVariable("id") String id) {
		//model.addAttribute("Movie",	moviepurApiService.getById(id));
		return "Add";
	}
	
	
}
