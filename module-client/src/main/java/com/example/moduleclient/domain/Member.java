package com.example.moduleclient.domain;
import com.example.moduleclient.dto.MemberJoinDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Table(name="member_tb")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mid;

    @Column(nullable = false, unique = true, length=50)
    private String username;

    @Column(name = "password",nullable = false, length=200)
    private String password;

    @Column(nullable = false, length=50)
    private String email;

    @Column(nullable = false, length=50)
    private String nickname;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false, length = 50)
    private MemberRole memberRole; //등급 권한

    //회원정보 저장
    public static Member createMember(MemberJoinDTO memberJoinDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setUsername(memberJoinDTO.getUsername());
        member.setEmail(memberJoinDTO.getEmail());
        member.setNickname(memberJoinDTO.getNickname());

        if (memberJoinDTO.getPassword() != null) {
            String password = passwordEncoder.encode(memberJoinDTO.getPassword());
            member.setPassword(password);
        }

        member.setCreatedAt(LocalDateTime.now());
        member.setMemberRole(MemberRole.새싹회원);
        return member;
    }

}
