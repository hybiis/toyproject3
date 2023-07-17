package com.example.moduleclient.controller;

import com.example.moduleclient.domain.Member;
import com.example.moduleclient.dto.MemberJoinDTO;
import com.example.moduleclient.repository.MemberRepository;
import com.example.moduleclient.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Validated
@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //로그인 페이지
    @GetMapping("/login")
    public String loginGET(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model){
        log.info("login get.............");
        log.info("logout: "+logout);

        // ?error을 이용해 로그인 실패 여부 확인
        if (error != null) {
            log.info("login error...................");
            model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        }

        //?logout을 이용해 로그아웃 여부 확인
        if(logout != null){
            log.info("user logout...................");
            model.addAttribute("logoutMessage", "로그아웃되었습니다.");
        }

        return "member/login";
    }


    //회원가입 페이지
    @GetMapping("/join")
    public String joinGET(Model model){

        log.info("join get.................................");
        model.addAttribute("memberJoinDTO", new MemberJoinDTO());
        return "member/join";

    }

    @PostMapping("/join")
    public String joinPOST(@Valid MemberJoinDTO memberJoinDTO, BindingResult bindingResult, Model model){

        log.info("join post..............");

        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        try {
            Member member = Member.createMember(memberJoinDTO, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/join";
        }

        return "redirect:/member/login";
    }

    //회원정보 수정 페이지
    @GetMapping("/updateForm")
    public void updateForm(){
        log.info("updateForm...............");
    }
}
