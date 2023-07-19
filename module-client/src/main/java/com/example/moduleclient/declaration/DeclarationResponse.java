package com.example.moduleclient.declaration;

import lombok.Getter;

public class DeclarationResponse {
	@Getter
	public static class SaveDto {
		private Long id;

		public SaveDto(Declaration declaration) {
			this.id = declaration.getId();
		}
	}
}
