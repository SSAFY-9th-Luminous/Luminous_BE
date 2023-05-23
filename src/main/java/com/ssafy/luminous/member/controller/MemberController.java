package com.ssafy.luminous.member.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.LoginRequestDto;
import com.ssafy.luminous.member.dto.LoginResponseDto;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.dto.MemberUpdateRequestDto;
import com.ssafy.luminous.member.service.MemberService;
import com.ssafy.luminous.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final JwtService jwtService;

    // 회원가입
    @PostMapping("/register")
    public BaseResponse<Object> register(@RequestBody RegisterRequestDto registerRequestDto) {
        try {
            memberService.register(registerRequestDto);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }

    // 로그인
    @PostMapping("/login")
    public BaseResponse<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Member member;
        try {
            member = memberService.login(loginRequestDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN);
        }

        String accessToken = jwtService.createAccessToken("id", member.getId());

        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, member);

        jwtService.setHeaderAccessToken(response, accessToken);
        return new BaseResponse<>(loginResponseDto);
    }

    @PutMapping("")
    public BaseResponse<MemberUpdateRequestDto> update(@RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
        try {
            memberService.updateMember(memberUpdateRequestDto);
        } catch (BaseException e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.NOT_MATCHED_ID);
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 회원탈퇴
    @DeleteMapping("/{id}")
    public BaseResponse<Object> deleteMember(@PathVariable("id") Long id) {
        if (memberService.deleteMember(id)) {
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }

        return new BaseResponse<>(BaseResponseStatus.FAILED_TO_DELETE);
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
    public BaseResponse<Member> findMemberByMemberId(@PathVariable("id") Long id) {
        Member member;
        try {
            member = memberService.findMemberById(id);
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.NOT_MATCHED_ID);
        }

        return new BaseResponse<>(member);
    }


}
