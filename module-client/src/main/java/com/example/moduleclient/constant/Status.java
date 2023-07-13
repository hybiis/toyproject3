package com.example.moduleclient.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
	SHOW("정상"),
	HIDE("숨김"),
	BLACKLIST("블랙리스트");

	private String name;

	public Status findByName(String name) {
		return Status.valueOf(name);
	}
}
