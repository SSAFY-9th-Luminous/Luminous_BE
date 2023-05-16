package com.ssafy.luminous.member.service;

import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public boolean register(RegisterRequestDto registerRequestDto) {
        Member newMember = Member
                .builder()
                .memberId(registerRequestDto.getMemberId())
                .memberPassword(registerRequestDto.getMemberPassword())
                .memberName(registerRequestDto.getMemberName())
                .birth(registerRequestDto.getBirth()).build();
        
        // constellationId 추가해주기

        if (newMember != null) {
            memberRepository.save(newMember);
            return true;
        }

        return false;
    }

    // 아이디 중복 체크
    public boolean isPossibleToUseMemberId(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            return false;
        }
        return true;
    }
}
