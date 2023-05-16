package com.ssafy.luminous.member.controller;

import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto registerRequestDto){
        memberService.register(registerRequestDto);
        
        // 토큰 리톤해야함
    }

    // 아이디 중복 체크
    @GetMapping("/check-id/{memberId}")
    public boolean checkId(@PathVariable("memberId") String memberId) {
        return memberService.isPossibleToUseMemberId(memberId);
    }


}
