package com.example.moduleclient.reply;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.modulecore.util.ApiUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;

	//@TODO: UserDetails 가져오기
	@PutMapping("/insert")
	public ResponseEntity<?> insertReply(@RequestBody ReplyRequest.saveDto saveReqDto) {
		ReplyResponse.SaveDto saveRespDto = replyService.saveReply(saveReqDto, 1L);

		return ResponseEntity.ok().body(ApiUtil.success(saveRespDto));
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteReply(@PathVariable Long id) {
		replyService.deleteReply(id);

		return ResponseEntity.ok().body(ApiUtil.success("OK"));
	}
}
