package com.example.moduleclient.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moduleclient.post.Post;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	List<Reply> findAllByPost(Post post);
}
