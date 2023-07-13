package com.example.moduleclient.reply;

import com.example.moduleclient.post.Post;
import com.example.moduleclient.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReplyRequest {
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class saveDto {
		private String comment;
		private Long parentId;
		private int step;
		private Long postId;

		public Reply toEntity(Post post, User user) {
			return Reply.builder()
				.comment(comment)
				.parentId(parentId)
				.step(step)
				.post(post)
				.user(user)
				.build();
		}
	}
}
