package com.example.moduleclient.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberRole {
	GENERAL("새싹회원"), BEST("우수회원");

	private String name;
}