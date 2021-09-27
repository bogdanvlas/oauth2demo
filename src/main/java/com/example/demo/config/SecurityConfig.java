package com.example.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.service.CustomOAuth2UserService;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private CustomOAuth2UserService userService;
	
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
		.userService(userService)
		;
	}

}
