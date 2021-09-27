package com.example.demo.model;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class UserInfo {
	protected Map<String, Object> attributes;

	public abstract String getLogin();
}
