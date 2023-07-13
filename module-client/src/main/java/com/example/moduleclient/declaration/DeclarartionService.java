package com.example.moduleclient.declaration;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moduleclient.post.Post;
import com.example.moduleclient.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeclarartionService {
	private final DeclarationRepository declarationRepository;
	private final PostRepository postRepository;

	@Transactional
	public DeclarationResponse.SaveDto saveDeclarartion(DeclarationRequest.SaveDto saveReqDto) {
		Post post = postRepository.findById(saveReqDto.getPostId()).orElseThrow();

		Declaration declaration = Declaration.builder()
			.reason(saveReqDto.getReason())
			.comment(saveReqDto.getComment())
			.reasonImage(saveReqDto.getReasonImage())
			.post(post)
			.build();

		Declaration declarationPs = declarationRepository.save(declaration);

		return new DeclarationResponse.SaveDto(declarationPs);
	}
}
