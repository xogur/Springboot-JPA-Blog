package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		return "/home.html";
	}
	
	@GetMapping("/temp/image")
	public String tempimage() {
		return "/(자두)계란.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempjsp() {
		return "test";
	}
}
