package com.example.moduleclient.reply;

import com.example.moduleclient.member.Member;
import com.example.moduleclient.post.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReplyRequest {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class saveDto {
		@NotBlank
		@Size(max = 50, message = "댓글은 50자를 넘을 수 없습니다.")
		private String comment;
		private Long parentId;
		@NotBlank
		private int step;
		@NotBlank
		private Long postId;

		public Reply toEntity(Post post, Member member) {
			return Reply.builder()
				.comment(comment)
				.parentId(parentId)
				.step(step)
				.post(post)
				.member(member)
				.build();
		}
	}
}
