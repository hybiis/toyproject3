package com.example.moduleclient.reply;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.moduleclient.member.Member;
import com.example.moduleclient.member.MemberRepository;
import com.example.moduleclient.post.Post;
import com.example.moduleclient.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;
	private final MemberRepository userRepository;
	private final PostRepository postRepository;

	public ReplyResponse.SaveDto saveReply(ReplyRequest.saveDto saveReqDto, String username) {
		Member member = userRepository.findByUsername(username);
		Post post = postRepository.findById(saveReqDto.getPostId()).orElseThrow();

		Reply reply = replyRepository.save(saveReqDto.toEntity(post, member));

		return new ReplyResponse.SaveDto(reply);
	}

	public void deleteReply(Long id) {
		Reply reply = replyRepository.findById(id).orElseThrow();

		replyRepository.delete(reply);
	}

	public List<ReplyResponse.DetailsDto> findReplyDetailsByPost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow();
		List<Reply> replies = replyRepository.findAllByPost(post);

		List<ReplyResponse.DetailsDto> replyDetailsDtos = replies.stream()
			.map(reply -> new ReplyResponse.DetailsDto(reply))
			.collect(
				Collectors.toList());

		return replyDetailsDtos;
	}
}
