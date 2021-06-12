package com.moviepur.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.moviepur.entity.Movie;
import com.moviepur.service.AdminApiService;

@Controller
public class AdminControllers {
	
	@Autowired
	private AdminApiService adminApiService;

	@GetMapping
	public String getAll(Model model, HttpServletResponse response) {
		response.addHeader("Cache-Control", "max-age=1000, must-revalidate, no-transform");
		model.addAttribute("list",adminApiService.getAllList());
		return "AllMovie";
	}
	
	@GetMapping("/search")
	public String searchByName(Model model, HttpServletResponse response,HttpServletRequest request) {
		response.addHeader("Cache-Control", "max-age=1000, must-revalidate, no-transform");
		model.addAttribute("list",adminApiService.searchByName(request.getParameter("moviename")));
		return "AllMovie";
	}
	
	@PostMapping("/fullDetails")
	public String getFullDetails(@RequestParam("id") int id,Model model) {
		//model.addAttribute("Title","Edit");
		//model.addAttribute("Movie",	adminApiService.getById(id));
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
	
	@GetMapping("/addWithFile")
	public String addWithFile(Model model) {
		model.addAttribute("title","Add");
		model.addAttribute("movie", new Movie());
		return "AddWithFile";
	}
	
	@PostMapping("/edit")
	public String getEditPage(@RequestParam("id") int id, Model model) {
		
		model.addAttribute("title","Edit");
		
		Movie movie = adminApiService.getById(id);
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
		
		movie.setRunTime(request.getParameter("hour")+"h "+ (request.getParameter("min").length() > 1 ? request.getParameter("min") :"0"+request.getParameter("min")) +"m");
		movie.setReleaseDate(LocalDate.parse(request.getParameter("y")+"-"+ (request.getParameter("m").length() > 1 ? request.getParameter("m") :"0"+request.getParameter("m"))+"-"+(request.getParameter("d").length() > 1 ? request.getParameter("d") :"0"+request.getParameter("d"))));
		movie.setDownload_link(IntStream.range(0, Arrays.asList(request.getParameterValues("downloadname")).size()).collect(LinkedHashMap::new, (m, i) -> m.put(Arrays.asList(request.getParameterValues("downloadname")).get(i), Arrays.asList(request.getParameterValues("downloadvalue")).get(i)), Map::putAll));
		if(movie.getId()==0) {
				adminApiService.save(movie);
		}else {
			adminApiService.update(movie.getId(), movie);
		}
		return new ModelAndView("redirect:"+"/");
	}
	
	@PostMapping("/saveWithFile")
	public ModelAndView saveWithFile(@RequestParam("thumbImage") MultipartFile image,@RequestParam("screenShot[]") MultipartFile[] screenShot, @RequestParam("downloads[]") MultipartFile[] downloadVideos, @ModelAttribute Movie movie,HttpServletRequest request ) {
		
		movie.setRunTime(request.getParameter("hour")+"h "+ (request.getParameter("min").length() > 1 ? request.getParameter("min") :"0"+request.getParameter("min")) +"m");
		movie.setReleaseDate(LocalDate.parse(request.getParameter("y")+"-"+ (request.getParameter("m").length() > 1 ? request.getParameter("m") :"0"+request.getParameter("m"))+"-"+(request.getParameter("d").length() > 1 ? request.getParameter("d") :"0"+request.getParameter("d"))));
		
		adminApiService.saveWithFile(movie, image, screenShot, downloadVideos);
		return new ModelAndView("redirect:"+"/");
	}
	
	@GetMapping("/updateFirebaseclss")
	public ModelAndView updateFirebaseclss() {
		adminApiService.firebaseClassUpdate();
		return new ModelAndView("redirect:"+"/");
	}
	
}
