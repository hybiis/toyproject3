package com.example.moduleclient.post;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.moduleclient.constant.Category;

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
}
