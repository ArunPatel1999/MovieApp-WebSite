package com.moviepur.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moviepur.entity.Movie;
import com.moviepur.service.LocalTesting;
import com.moviepur.service.MoviepurApiService;

@Controller
public class PageController {

	@Autowired
	private MoviepurApiService moviepurApiService;
	
	@Autowired
	private LocalTesting localTesting;

	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("list",localTesting.getAllList());
		return "AllMovie";
	}
	
	@PostMapping("/fullDetails")
	public String getFullDetails(@RequestParam("id") int id,Model model) {
		//model.addAttribute("Title","Edit");
		//model.addAttribute("Movie",	moviepurApiService.getById(id));
		return "Add";
	}
	
	
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "Login";
	}
	
	@GetMapping("/add")
	public String getAddPage(Model model) {
		model.addAttribute("title","Add");
		model.addAttribute("movie", new Movie());
		return "Add";
	}
	
	@PostMapping("/edit")
	public String getEditPage(@RequestParam("id") int id, Model model) {
		
		model.addAttribute("title","Edit");
		
		Movie movie = localTesting.getById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("hour",movie.getRunTime().charAt(0));
		model.addAttribute("min",movie.getRunTime().substring(3, 5));
		model.addAttribute("d",movie.getReleaseDate().getDayOfMonth());
		model.addAttribute("m",movie.getReleaseDate().getMonthValue());
		model.addAttribute("y",movie.getReleaseDate().getYear());
		return "Add";
	}
	
	@PostMapping("/save")
	public ModelAndView saveData(@ModelAttribute Movie movie ,HttpServletRequest request) {
		
		movie.setRunTime(request.getParameter("hour")+"h "+request.getParameter("min")+"m");
		movie.setReleaseDate(LocalDate.parse(request.getParameter("y")+"-"+request.getParameter("m")+"-"+request.getParameter("d")));
		movie.setDownload_link(IntStream.range(0, Arrays.asList(request.getParameterValues("downloadname")).size()).collect(LinkedHashMap::new, (m, i) -> m.put(Arrays.asList(request.getParameterValues("downloadname")).get(i), Arrays.asList(request.getParameterValues("downloadvalue")).get(i)), Map::putAll));
		if(movie.getId()==0) {
				localTesting.save(movie);
		}else {
			localTesting.update(movie.getId(), movie);
		}
		return new ModelAndView("redirect:"+"/");
	}
	
}
