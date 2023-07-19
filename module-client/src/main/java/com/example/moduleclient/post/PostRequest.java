package com.example.moduleclient.post;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.constant.Status;
import com.example.moduleclient.member.Member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostRequest {
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class saveDto {
		@NotBlank
		@Size(max = 30, message = "제목은 30자를 넘을 수 없습니다.")
		private String title;
		@NotBlank
		@Size(max = 2000, message = "내용은 2000자를 넘을 수 없습니다.")
		private String content;

		private String thumbnail;
		@NotBlank
		private Category category;

		public Post toEntity(Member member) {
			return Post.builder()
				.title(title)
				.content(content)
				.thumbnail(thumbnail)
				.category(category)
				.status(Status.SHOW)
				.member(member)
				.build();
		}
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UpdateDto {
		@NotBlank
		private Long id;

		@NotBlank
		@Size(max = 30, message = "제목은 30자를 넘을 수 없습니다.")
		private String title;
		@NotBlank
		@Size(max = 2000, message = "내용은 2000자를 넘을 수 없습니다.")
		private String content;

		public UpdateDto(Post post) {
			this.id = post.getId();
			this.title = post.getTitle();
			this.content = post.getContent();
		}
	}
}
