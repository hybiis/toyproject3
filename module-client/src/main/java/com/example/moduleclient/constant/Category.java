package com.example.moduleclient.constant;

import java.util.ArrayList;
import java.util.List;

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

	public static List<Category> findByMemberRole(MemberRole memberRole) {
		List<Category> categories = new ArrayList<>();

		categories.add(GENERAL);

		if (memberRole == MemberRole.BEST) {
			categories.add(BEST);
		}

		return categories;
	}
}
