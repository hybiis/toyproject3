package com.example.moduleclient.post;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.constant.Status;
import com.example.moduleclient.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequest {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class saveDto {
		private String title;
		private String content;
		private byte[] thumbnail;
		private Category category;

		public Post toEntity(User user) {
			return Post.builder()
				.title(title)
				.content(content)
				.thumbnail(thumbnail)
				.category(category)
				.status(Status.SHOW)
				.user(user)
				.build();
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class updateDto {
		private String title;
		private String content;
	}
}
