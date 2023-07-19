package com.example.moduleclient.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
	GENERAL("새싹회원"),
	BEST("우수회원");

	private String name;

	public Category findByName(String name) {
		return Category.valueOf(name);
	}
}
