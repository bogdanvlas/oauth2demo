package com.example.demo.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.GoogleUserInfo;
import com.example.demo.model.User;
import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomOidcUserService extends OidcUserService {
	private UserRepository userRepository;

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser oidcUser = super.loadUser(userRequest);
		String provider = userRequest.getClientRegistration().getRegistrationId();

		UserInfo userInfo = null;
		if (provider.equalsIgnoreCase("google")) {
			userInfo = new GoogleUserInfo(oidcUser.getAttributes());
		}
		boolean exists = userRepository.existsByLoginAndProvider(userInfo.getLogin(), provider);
		if (!exists) {
			User user = new User();
			user.setLogin(userInfo.getLogin());
			user.setProvider(provider);
			userRepository.save(user);
		}
		return oidcUser;
	}
}
