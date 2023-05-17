package com.ssafy.luminous.member.controller;

import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.LoginRequestDto;
import com.ssafy.luminous.member.dto.LoginResponseDto;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.service.MemberService;
import com.ssafy.luminous.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final JwtService jwtService;

    // 회원가입
    @PostMapping("/register")
    public boolean register(@RequestBody RegisterRequestDto registerRequestDto){
        return memberService.register(registerRequestDto);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        Member member = memberService.login(loginRequestDto);

        String accessToken = jwtService.createAccessToken("id", member.getId());

        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, member);

        return loginResponseDto;
    }

    // 아이디 중복 체크
    @GetMapping("/check-id/{memberId}")
    public boolean checkId(@PathVariable("memberId") String memberId) {
        return memberService.isPossibleToUseMemberId(memberId);

    }

    // 아이디로 사용자 검색
    @GetMapping("/detail/{id}")
    public Member findMemberByMemberId(@PathVariable("id") Long id) {

        return memberService.findMemberById(id);
    }


}
