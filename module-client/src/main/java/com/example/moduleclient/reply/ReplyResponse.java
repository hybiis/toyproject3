package com.example.moduleclient.reply;

import com.example.modulecore.util.MyDateUtil;

import lombok.Getter;

public class ReplyResponse {
	@Getter
	public static class SaveDto {
		private String comment;
		private Long parentId;
		private int step;

		public SaveDto(Reply reply) {
			this.comment = reply.getComment();
			this.parentId = reply.getParentId();
			this.step = reply.getStep();
		}
	}

	public static class DeleteDto {
		private Long id;
		private String comment;

		public DeleteDto(Reply reply) {
			this.id = reply.getId();
			this.comment = reply.getComment();
		}
	}

	@Getter
	public static class DetailsDto {
		private Long id;
		private String comment;
		private Long parentId;
		private int step;
		private String nickname;
		private String createdAt;

		public DetailsDto(Reply reply) {
			this.id = reply.getId();
			this.comment = reply.getComment();
			this.parentId = reply.getParentId();
			this.step = reply.getStep();
			this.nickname = reply.getMember().getNickname();
			this.createdAt = MyDateUtil.toDateFormat(reply.getCreatedAt());
		}
	}
}
