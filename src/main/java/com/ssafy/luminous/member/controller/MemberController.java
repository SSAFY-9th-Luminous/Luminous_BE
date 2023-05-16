package com.ssafy.luminous.member.controller;

import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
