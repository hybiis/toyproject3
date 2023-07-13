package com.example.moduleclient.reply;

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
}
