package com.example.moduleclient.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Reason {
	ABUSE("욕설"),
	LEWDNESS("음란"),
	CALUMNY("비방"),
	ADVERTISEMENT("광고"),
	ETC("기타");

	private String name;
}
