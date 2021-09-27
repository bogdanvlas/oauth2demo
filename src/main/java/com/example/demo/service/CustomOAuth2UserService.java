package com.example.demo.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.model.GitHubUserInfo;
import com.example.demo.model.User;
import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = super.loadUser(userRequest);
		// логика...
		String provider = userRequest.getClientRegistration().getRegistrationId();

		UserInfo userInfo = null;
		if (provider.equalsIgnoreCase("github")) {
			userInfo = new GitHubUserInfo(oauth2User.getAttributes());
		}

		boolean exists = userRepository.existsByLoginAndProvider(userInfo.getLogin(), provider);
		if (!exists) {
			User user = new User();
			user.setLogin(userInfo.getLogin());
			user.setProvider(provider);
			userRepository.save(user);
		}
		return oauth2User;
	}
}
