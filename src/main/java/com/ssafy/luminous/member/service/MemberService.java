package com.ssafy.luminous.member.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.LoginRequestDto;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.dto.MemberUpdateRequestDto;
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

    // 로그인
    public Member login(LoginRequestDto loginRequestDto) throws IllegalArgumentException {
        Optional<Member> member = memberRepository.findByMemberId(loginRequestDto.getMemberId());

        // 아이디가 다를 경우
        if (member.isEmpty()) {
            throw new IllegalArgumentException("아이디가 일치하지 않습니다");
        }
        // 비밀번호 비교
        if (!validatePassword(member.get().getMemberPassword(), loginRequestDto.getMemberPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member.get();
    }

    // 회원 정보 수정
    public Member updateMember(MemberUpdateRequestDto memberUpdateRequestDto) throws BaseException {
        Optional<Member> member = memberRepository.findById(memberUpdateRequestDto.getId());

        if (member.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_MATCHED_ID);
        }

        member.get().update(memberUpdateRequestDto);
        return member.get();
    }

    // 회원탈퇴
    public boolean deleteMember(Long id) {
        try {
            memberRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    // 아이디 중복 체크
    public boolean isPossibleToUseMemberId(String memberId) {
        Optional<Member> member = memberRepository.findByMemberId(memberId);
        return member.isEmpty();
    }

    // 비밀번호 확인
    public boolean validatePassword(String memberPassword, String inputPassword) {
        if (memberPassword.equals(inputPassword))
            return true;

        return false;
    }

    // 아이디로 사용자 검색
    public Member findMemberById(Long id) throws IllegalArgumentException{
        Optional<Member> member = memberRepository.findById(id);

        if(member.isPresent()) {
            return member.get();
        }
        throw new IllegalArgumentException("존재하지 않은 사용자입니다.");
    }
}
