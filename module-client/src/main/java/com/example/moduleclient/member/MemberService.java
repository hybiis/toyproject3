package com.example.moduleclient.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moduleclient.constant.Category;
import com.example.moduleclient.constant.ErrorCode;
import com.example.moduleclient.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Transactional
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;

	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member);
	}

	//이미 가입된 회원의 경우 예외 처리
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByUsername(member.getUsername()).orElseThrow();
		if (findMember != null) {
			throw new CustomException(ErrorCode.MEMBER_USERNAME_DUPLICATED);
		}
	}

	//실제 인증 처리할 떄 호출되는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("user name: " + username);

		Member member = memberRepository.findByUsername(username)
			.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

		List<Category> categories = Category.findByMemberRole(member.getMemberRole());
		List<SimpleGrantedAuthority> authorities = categories.stream()
			.map(category -> new SimpleGrantedAuthority(category.toString()))
			.collect(Collectors.toList());

		return User.builder()
			.username(member.getUsername())
			.password(member.getPassword())
			.authorities(authorities)
			.build();
	}
}