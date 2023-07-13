package com.example.moduleclient.reply;

import org.springframework.stereotype.Service;

import com.example.moduleclient.post.Post;
import com.example.moduleclient.post.PostRepository;
import com.example.moduleclient.user.User;
import com.example.moduleclient.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public ReplyResponse.SaveDto saveReply(ReplyRequest.saveDto saveReqDto, Long userId) {
		User user = userRepository.findById(userId).orElseThrow();
		Post post = postRepository.findById(saveReqDto.getPostId()).orElseThrow();

		Reply reply = replyRepository.save(saveReqDto.toEntity(post, user));

		return new ReplyResponse.SaveDto(reply);
	}

	public void deleteReply(Long id) {
		Reply reply = replyRepository.findById(id).orElseThrow();

		replyRepository.delete(reply);
	}
}
