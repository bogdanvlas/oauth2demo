package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	@GetMapping("/oauth_login")
	public String loginPage() {
		return "loginPage";
	}
}
