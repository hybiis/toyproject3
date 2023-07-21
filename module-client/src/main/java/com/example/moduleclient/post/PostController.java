package com.example.moduleclient.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.constant.SearchType;
import com.example.moduleclient.reply.ReplyResponse;
import com.example.moduleclient.reply.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	private final ReplyService replyService;

	@PreAuthorize("hasAuthority(T(java.util.Objects).requireNonNullElse(#category, 'GENERAL'))")
	@GetMapping({"/boards", "/"})
	public String list(
		@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
		@RequestParam(defaultValue = "GENERAL") Category category,
		@RequestParam(required = false, name = "type") SearchType searchType,
		@RequestParam(required = false) String keyword, Model model, Pageable pageable) {

		pageNo = pageNo == 0 ? 0 : pageNo - 1;
		Page<PostPagesDto> postPages = null;
		if (keyword == null) {
			postPages = postService.list(pageNo, category, pageable);
		} else {
			postPages = postService.searchPosts(pageNo, searchType, keyword, category, pageable);
		}

		model.addAttribute("list", postPages);
		model.addAttribute("searchType", SearchType.values());
		model.addAttribute("categories", Category.values());

		return "board/list";
	}

	@GetMapping("/boards/{id}")
	public String detailForm(@PathVariable Long id, Model model) {
		PostResponse.DetailsDto postDetailsDto = postService.findDetailsByPost(id);
		List<ReplyResponse.DetailsDto> replyDeatilsDto = replyService.findReplyDetailsByPost(id);

		model.addAttribute("post", postDetailsDto);
		model.addAttribute("replies", replyDeatilsDto);

		return "board/details";
	}

	@GetMapping("/board/save")
	public String saveForm(Model model, PostRequest.saveDto saveReqDto) {

		model.addAttribute("categories", Category.values());
		model.addAttribute("saveReqDto", saveReqDto);
		return "/board/save";
	}

	@PreAuthorize("hasAuthority(T(java.util.Objects).requireNonNullElse(#saveReqDto.category, 'GENERAL'))")
	@PostMapping("/api/board/save")
	public String savePost(@Valid PostRequest.saveDto saveReqDto,
		@AuthenticationPrincipal UserDetails userDetails) {
		PostResponse.SaveDto saveRespDto = postService.savePost(saveReqDto, userDetails.getUsername());
		return "redirect:/boards/" + saveRespDto.getId();
	}

	@DeleteMapping("/boards/{id}/delete")
	public String deletePost(@PathVariable Long id) {
		PostResponse.DeleteDto deleteRespDto = postService.deletePost(id);

		return "redirect:/boards";
	}

	@GetMapping("/boards/{id}/edit")
	public String updateForm(@PathVariable Long id, PostRequest.UpdateDto updateReqDto, Model model) {
		updateReqDto = postService.findById(id);

		model.addAttribute("updateReqDto", updateReqDto);

		return "board/edit";
	}
	
	@PreAuthorize("hasAuthority(T(java.util.Objects).requireNonNullElse(#updateReqDto.category, 'GENERAL'))")
	@PutMapping("/api/boards/{id}/update")
	public String updatePost(@PathVariable Long id, @Valid PostRequest.UpdateDto updateReqDto) {
		PostResponse.UpdateDto updateRespDto = postService.updatePost(updateReqDto, id);

		return "redirect:/boards/{id}";
	}
}
