package com.example.moduleclient.post;

import lombok.Getter;

public class PostResponse {
	@Getter
	public static class SaveDto {
		private Long id;
		private String title;
		private String content;

		public SaveDto(Post post) {
			this.id = post.getId();
			this.title = post.getTitle();
			this.content = post.getContent();
		}
	}
}
