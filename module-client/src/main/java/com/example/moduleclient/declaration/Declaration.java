package com.example.moduleclient.declaration;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.moduleclient.constant.Reason;
import com.example.moduleclient.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "declaration_tb")
public class Declaration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private Reason reason;
	@Column(length = 100)
	private String comment;
	@Lob
	private byte[] reasonImage;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

}
