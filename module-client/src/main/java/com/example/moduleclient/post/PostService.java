package com.example.moduleclient.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.constant.ErrorCode;
import com.example.moduleclient.constant.SearchType;
import com.example.moduleclient.exception.CustomException;
import com.example.moduleclient.member.Member;
import com.example.moduleclient.member.MemberRepository;
import com.example.modulecore.image.ImageUpload;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final EntityManager entityManager;

	public Page<PostPagesDto> list(int pageNo, Category category, Pageable pageable) {
		pageable = PageRequest.of(pageNo, 6, Sort.by("id").descending());

		Page<PostPagesDto> postPagesRespDto = postRepository.findByCategory(category, pageable);

		return postPagesRespDto;
	}

	public Page<PostPagesDto> searchPosts(int pageNo, SearchType searchType, String keyword, Category category,
		Pageable pageable) {

		pageable = PageRequest.of(pageNo, 6, Sort.by("id").descending());
		Page<PostPagesDto> postPagesRespDto = null;

		if (searchType == SearchType.T) {
			postPagesRespDto = postRepository.findByTitleContaining(keyword, category, pageable);
		} else if (searchType == SearchType.C) {
			postPagesRespDto = postRepository.findByContentContaining(keyword, category, pageable);
		} else if (searchType == SearchType.N) {
			postPagesRespDto = postRepository.findByNicknameContaining(keyword, category, pageable);
		}

		return postPagesRespDto;
	}

	public PostResponse.DetailsDto findDetailsByPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		PostResponse.DetailsDto detailsDto = new PostResponse.DetailsDto(post);

		return detailsDto;
	}

	@Transactional
	public PostResponse.SaveDto savePost(PostRequest.saveDto saveReqDto, String username) {
		Member member = memberRepository.findByUsername(username)
			.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

		String uploadThumbnail = getUploadThumbnail(saveReqDto.getThumbnailImage());
		saveReqDto.setThumbnail(uploadThumbnail);

		Post post = postRepository.save(saveReqDto.toEntity(member));

		return new PostResponse.SaveDto(post);
	}

	//@TODO: 본인 게시글인지 권한 체크 필요
	@Transactional
	public PostResponse.DeleteDto deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		postRepository.delete(post);

		return new PostResponse.DeleteDto(post);
	}

	//@TODO: 본인 게시글인지 권한 체크 필요
	@Transactional
	public PostResponse.UpdateDto updatePost(PostRequest.UpdateDto updateReqDto, Long id) {
		Post post = entityManager.find(Post.class, id);

		if (post == null)
			throw new CustomException(ErrorCode.POST_NOT_FOUND);

		post.setTitle(updateReqDto.getTitle());
		post.setContent(updateReqDto.getContent());

		PostResponse.UpdateDto updateRespDto = new PostResponse.UpdateDto(post);

		return updateRespDto;
	}

	public PostRequest.UpdateDto findById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

		return new PostRequest.UpdateDto(post);
	}

	private String getUploadThumbnail(MultipartFile originThumbnail) {
		if (originThumbnail == null)
			return null;

		return ImageUpload.upload(originThumbnail, "thumbnails");
	}
}
