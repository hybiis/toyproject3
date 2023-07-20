package com.example.moduleclient.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.moduleclient.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Log4j2
public class SecurityConfig {
	private final DataSource dataSource;
	private final MemberService memberService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		log.info("----------------configure-------------------------");

		http.formLogin((formLogin) -> formLogin.loginPage("/member/login").defaultSuccessUrl("/"));
		http.csrf(AbstractHttpConfigurer::disable);

		http.rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer.key("12345678")
			.tokenRepository(persistentTokenRepository())
			.userDetailsService(memberService)
			.tokenValiditySeconds(60 * 60 * 24 * 30));

		http.logout(logout -> logout.logoutSuccessUrl("/member/login"));

		http.authorizeHttpRequests(auth -> auth
			.requestMatchers("/member/login", "/member/join").permitAll()
			.anyRequest().authenticated());

		return http.build();
	}

	//자동 로그인 쿠키 값 인코딩을 위한 키 값, 정보 저장 메서드
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {

		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);

		return repo;
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	//정적자원 시큐리티 적용 제외(ex. css)
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

		log.info("----------------web configure-----------------------------");

		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
}
