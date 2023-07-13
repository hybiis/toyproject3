package com.example.moduleclient.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.moduleclient.constant.Category;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("SELECT new com.example.moduleclient.post.PostPagesDto(p.id, p.title, u.nickname, p.createdAt)"
		+ " FROM Post p JOIN p.user u WHERE p.category = :category")
	Page<PostPagesDto> findByCategory(@Param("category") Category category, Pageable pageable);
}
