package com.example.moduleclient.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPagesDto {
	private Long id;
	private String title;
	private String nickname;
	private LocalDateTime createdAt;
}
