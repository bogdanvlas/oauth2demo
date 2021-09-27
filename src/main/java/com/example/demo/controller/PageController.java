package com.example.demo.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.GitHubUserInfo;
import com.example.demo.model.GoogleUserInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.model.UserInfoFactory;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PageController {
	
	private UserInfoFactory userInfoFactory;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/login/oauth2")
	public String loginPage() {
		return "loginPage";
	}

	@GetMapping("/secured")
	public String securedPage(OAuth2AuthenticationToken token, Model m) {

		String provider = token.getAuthorizedClientRegistrationId();
		UserInfo userInfo = userInfoFactory.create(provider, token.getPrincipal().getAttributes());
		m.addAttribute("login", userInfo.getLogin());
		m.addAttribute("provider", provider);
		return "securedPage";
	}
}
