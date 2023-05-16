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

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto registerRequestDto){
        memberService.register(registerRequestDto);
        
        // 토큰 리톤해야함
    }

    @GetMapping("/check-id/{memberId}")
    public boolean checkId(@PathVariable("memberId") String memberId) {
        return memberService.checkId(memberId);
    }


}
