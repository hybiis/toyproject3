package com.example.moduleclient.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.time.LocalDate;


@Getter
@Setter
@Data
public class MemberJoinDTO {

    private String username;

    private String password;

    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    private String nickname;

    private LocalDate created_at;

}
