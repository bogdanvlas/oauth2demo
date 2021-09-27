package com.example.demo.model;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserInfoFactory {
	public UserInfo create(String provider, Map<String, Object> attr) {
		UserInfo userInfo = null;
		if (provider.equalsIgnoreCase("github")) {
			userInfo = new GitHubUserInfo(attr);
		}
		if (provider.equalsIgnoreCase("google")) {
			userInfo = new GoogleUserInfo(attr);
		}
		return userInfo;
	}
}
