package com.example.demo.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
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
		String login = oidcUser.getAttribute("email");
		boolean exists = userRepository.existsByLoginAndProvider(login, provider);
		if (!exists) {
			User user = new User();
			user.setLogin(login);
			user.setProvider(provider);
			userRepository.save(user);
		}
		return oidcUser;
	}
}
