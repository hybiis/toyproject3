package com.example.moduleclient.post;

import com.example.modulecore.util.MyDateUtil;

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

	@Getter
	public static class DeleteDto {
		private Long id;
		private String title;
		private String content;

		public DeleteDto(Post post) {
			this.id = post.getId();
			this.title = post.getTitle();
			this.content = post.getContent();
		}
	}

	@Getter
	public static class UpdateDto {
		private Long id;
		private String title;
		private String content;

		public UpdateDto(Post post) {
			this.id = post.getId();
			this.title = post.getTitle();
			this.content = post.getContent();
		}
	}

	@Getter
	public static class DetailsDto {
		private Long id;
		private String title;
		private String content;
		private String nickname;
		private String createdAt;

		public DetailsDto(Post post) {
			this.id = post.getId();
			this.title = post.getTitle();
			this.content = post.getContent();
			this.nickname = post.getMember().getNickname();
			this.createdAt = MyDateUtil.toDateFormat(post.getCreatedAt());
		}
	}
}
