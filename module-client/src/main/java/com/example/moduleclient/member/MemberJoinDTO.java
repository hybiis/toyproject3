package com.example.moduleclient.member;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MemberJoinDTO {

	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String username;

	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
	private String password;

	@NotEmpty(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String email;

	@NotEmpty(message = "닉네임은 필수 입력 값입니다.")
	private String nickname;

	private LocalDate created_at;

}