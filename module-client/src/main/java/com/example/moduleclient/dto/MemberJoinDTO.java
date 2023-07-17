package com.example.moduleclient.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@Data
public class MemberJoinDTO {

    @NotNull(message = "아이디는 필수 입력 값입니다.")
    private String username;

    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotNull(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotNull(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    private LocalDate created_at;

}
