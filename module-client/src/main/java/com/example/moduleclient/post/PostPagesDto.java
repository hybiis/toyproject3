package com.example.moduleclient.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPagesDto {
	private Long id;
	private String title;
	private String content;
	private String thumbnail;
	private String nickname;
	private LocalDateTime createdAt;
}
