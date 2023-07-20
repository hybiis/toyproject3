package com.example.moduleclient.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SearchType {
	N("닉네임"), T("제목"), C("내용");

	public String name;
}
