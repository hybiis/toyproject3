package com.example.moduleclient.reply;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.modulecore.util.ApiUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;

	@PutMapping("/reply/insert")
	public ResponseEntity<?> insertReply(@RequestBody ReplyRequest.saveDto saveReqDto, @AuthenticationPrincipal
	UserDetails userDetails) {
		ReplyResponse.SaveDto saveRespDto = replyService.saveReply(saveReqDto, userDetails.getUsername());

		return ResponseEntity.ok().body(ApiUtil.success(saveRespDto));
	}

	@DeleteMapping("/reply/{id}/delete")
	public ResponseEntity<?> deleteReply(@PathVariable Long id) {
		replyService.deleteReply(id);

		return ResponseEntity.ok().body(ApiUtil.success("OK"));
	}
}
