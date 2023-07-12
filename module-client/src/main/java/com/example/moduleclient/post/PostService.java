package com.example.moduleclient.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.moduleclient.constant.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public Page<PostPagesDto> list(int pageNo, Category category) {
		Pageable pageable = PageRequest.of(pageNo, 6, Sort.by(Sort.Direction.DESC, "id"));
		Page<PostPagesDto> postPagesRespDto = postRepository.findByCategory(category, pageable);

		return postPagesRespDto;
	}
}
