package com.example.moduleclient.post;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.constant.Status;
import com.example.moduleclient.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequest {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class saveDto {
		@NotBlank
		@Size(max = 30, message = "제목은 30자를 넘을 수 없습니다.")
		private String title;
		@NotBlank
		@Size(max = 2000, message = "내용은 2000자를 넘을 수 없습니다.")
		private String content;

		private byte[] thumbnail;
		@NotBlank
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
		@NotBlank
		@Size(max = 30, message = "제목은 30자를 넘을 수 없습니다.")
		private String title;
		@NotBlank
		@Size(max = 2000, message = "내용은 2000자를 넘을 수 없습니다.")
		private String content;
	}
}
