package com.example.moduleclient.member;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.moduleclient.constant.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "member_tb")
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mid;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(name = "password", nullable = false, length = 200)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 50)
	private String nickname;

	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "member_role", nullable = false, length = 50)
	private MemberRole memberRole; //등급 권한

	//회원정보 저장
	public static Member createMember(MemberJoinDTO memberJoinDTO, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setUsername(memberJoinDTO.getUsername());
		member.setEmail(memberJoinDTO.getEmail());
		member.setNickname(memberJoinDTO.getNickname());

		if (memberJoinDTO.getPassword() != null) {
			String password = passwordEncoder.encode(memberJoinDTO.getPassword());
			member.setPassword(password);
		}

		member.setCreatedAt(LocalDateTime.now());
		member.setMemberRole(MemberRole.GENERAL);
		return member;
	}

}