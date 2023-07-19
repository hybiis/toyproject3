package com.example.moduleclient.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.member.Member;
import com.example.moduleclient.member.MemberRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final EntityManager entityManager;

	public Page<PostPagesDto> list(int pageNo, Category category, Pageable pageable) {
		pageNo = pageNo == 0 ? 0 : pageNo - 1;
		Page<PostPagesDto> postPagesRespDto = postRepository.findByCategory(category, pageable);

		return postPagesRespDto;
	}

	public PostResponse.DetailsDto findDetailsByPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow();

		PostResponse.DetailsDto detailsDto = new PostResponse.DetailsDto(post);

		return detailsDto;
	}

	public PostResponse.SaveDto savePost(PostRequest.saveDto saveReqDto, String username) {
		Member member = memberRepository.findByUsername(username);
		Post post = postRepository.save(saveReqDto.toEntity(member));

		return new PostResponse.SaveDto(post);
	}

	//@TODO: 본인 게시글인지 권한 체크 필요
	public PostResponse.DeleteDto deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow();

		postRepository.delete(post);

		return new PostResponse.DeleteDto(post);
	}

	//@TODO: 본인 게시글인지 권한 체크 필요
	public PostResponse.UpdateDto updatePost(PostRequest.UpdateDto updateReqDto, Long id) {
		Post post = entityManager.find(Post.class, id);
		post.setTitle(updateReqDto.getTitle());
		post.setContent(updateReqDto.getContent());

		PostResponse.UpdateDto updateRespDto = new PostResponse.UpdateDto(post);

		return updateRespDto;
	}

	public Page<PostPagesDto> searchByKeyword(int pageNo, int gubun, String keyword) {
		Pageable pageable = PageRequest.of(pageNo, 6, Sort.by(Sort.Direction.DESC, "id"));
		Page<PostPagesDto> postPagesRespDto = null;

		switch (gubun) {
			case 1:
				postPagesRespDto = postRepository.findByNicknameContaining(keyword, pageable);
				break;

			case 2:
				postPagesRespDto = postRepository.findByTitleContaining(keyword, pageable);
				break;

			case 3:
				postPagesRespDto = postRepository.findByContentContaining(keyword, pageable);
				break;
		}

		return postPagesRespDto;
	}

	public PostRequest.UpdateDto findById(Long id) {
		Post post = postRepository.findById(id).orElseThrow();

		return new PostRequest.UpdateDto(post);
	}
}
