package com.ssafy.luminous.member.controller;

import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
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
    public BaseResponse<Object> register(@RequestBody RegisterRequestDto registerRequestDto){
        if (memberService.register(registerRequestDto)) {
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }

        return new BaseResponse<>(BaseResponseStatus.FAILED_TO_REGISTER);
    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        Member member;
        try {
            member = memberService.login(loginRequestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN);
        }

        String accessToken = jwtService.createAccessToken("id", member.getId());

        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, member);

        return new BaseResponse<>(loginResponseDto);
    }

    // 아이디 중복 체크
    @GetMapping("/check-id/{memberId}")
    public BaseResponse<Object> checkId(@PathVariable("memberId") String memberId) {

        if (memberService.isPossibleToUseMemberId(memberId)) {
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }

        return new BaseResponse<>(BaseResponseStatus.DUPLICATED_ID);

    }

    // 아이디로 사용자 검색
    @GetMapping("/detail/{id}")
    public Member findMemberByMemberId(@PathVariable("id") Long id) {

        return memberService.findMemberById(id);
    }


}
