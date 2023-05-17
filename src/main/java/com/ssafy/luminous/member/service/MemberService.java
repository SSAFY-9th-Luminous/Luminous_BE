package com.ssafy.luminous.member.service;

import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.LoginRequestDto;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.repository.MemberRepository;
import com.ssafy.luminous.util.jwt.JwtService;
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

    // 로그인
    public Member login(LoginRequestDto loginRequestDto) {
        Optional<Member> member = memberRepository.findByMemberId(loginRequestDto.getMemberId());

        // 해당 아이디가 존재할 경우
        if (!member.isPresent()) {
            throw new IllegalArgumentException("존재하지 않은 아이디 입니다.");
        }
        // 비밀번호 비교
        isValidPassword(member.get().getMemberPassword(), loginRequestDto.getMemberPassword());

        return member.get();
    }

    // 아이디 중복 체크
    public boolean isPossibleToUseMemberId(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        if (member.isPresent()) {
            return false;
        }
        return true;
    }

    // 비밀번호 확인
    public boolean isValidPassword(String memberPassword, String inputPassword) {
        if (memberPassword.equals(inputPassword)) {
            return true;
        }
        throw new IllegalArgumentException("비밀번호가 다릅니다.");
    }
}
