package com.example.moduleclient.declaration;

import com.example.moduleclient.constant.Reason;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DeclarationRequest {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SaveDto {
		private Reason reason;
		private String comment;
		private byte[] reasonImage;
		private Long postId;
	}
}
