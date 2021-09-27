package com.example.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.service.CustomOAuth2UserService;
import com.example.demo.service.CustomOidcUserService;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private CustomOAuth2UserService userService;
	
	private CustomOidcUserService oidcUserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/index").permitAll()
		.antMatchers("/secured/**").authenticated()
		.and()
		.oauth2Login()
		.loginPage("/login/oauth2")
		.defaultSuccessUrl("/secured")
		.userInfoEndpoint()
		.userService(userService)//oauth2 - github
		.oidcUserService(oidcUserService)//OpenId Connect - google
		;
	}

}
