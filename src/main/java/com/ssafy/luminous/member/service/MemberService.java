package com.ssafy.luminous.member.service;

import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean register(RegisterRequestDto registerRequestDto) {
        Member newMember = Member.register(registerRequestDto);

        if (newMember != null) {
            memberRepository.save(newMember);
            return true;
        }

        return false;
    }
}
