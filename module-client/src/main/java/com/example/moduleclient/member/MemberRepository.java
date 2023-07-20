package com.example.moduleclient.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
	//usernaem 증복 체크
	Member findByUsername(String username);
}
