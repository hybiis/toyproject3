package com.example.moduleclient.post;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.util.ApiUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping("/boards")
	@ResponseBody
	public Page<PostPagesDto> list(
		@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo) {
		pageNo = pageNo == 0 ? 0 : pageNo - 1;

		Page<PostPagesDto> postPages = postService.list(pageNo, Category.DEFAULT);
		return postPages;
	}

	@PutMapping("/insert")
	@ResponseBody
	//@TODO: UserDetails 가져오기
	public ResponseEntity<?> savePost(@RequestBody PostRequest.saveDto saveDto) {
		PostResponse.SaveDto saveRespDto = postService.savePost(saveDto, 1L);
		return ResponseEntity.ok().body(ApiUtil.success(saveRespDto));
	}
}
