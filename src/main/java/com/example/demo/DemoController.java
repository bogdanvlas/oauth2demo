package com.example.demo;

import java.security.Principal;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/free")
	public String freeEndpoint() {
		return "This endpoint is free";
	}

	@GetMapping("/secured")
	public String securedEndpoint(OAuth2AuthenticationToken token) {
		OAuth2User user = token.getPrincipal();
		return "This endpoint is secured: you logged in as " + user.getAttribute("login");
	}
}
