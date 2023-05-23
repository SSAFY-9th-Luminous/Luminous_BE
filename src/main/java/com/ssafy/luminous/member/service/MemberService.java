package com.ssafy.luminous.member.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.repository.Constellation12Repository;
import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.dto.LoginRequestDto;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.dto.MemberUpdateRequestDto;
import com.ssafy.luminous.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

import static com.ssafy.luminous.config.BaseResponseStatus.DUPLICATED_ID;
import static com.ssafy.luminous.config.BaseResponseStatus.FAILED_TO_REGISTER;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Constellation12Repository constellation12Repository;

    // 회원가입
    @Transactional
    public void register(RegisterRequestDto registerRequestDto) throws BaseException {
        Member newMember = Member
                .builder()
                .memberId(registerRequestDto.getMemberId())
                .memberPassword(registerRequestDto.getMemberPassword())
                .memberName(registerRequestDto.getMemberName())
                .birth(registerRequestDto.getBirth()).build();

        // 2000년으로 바운딩
        Date birth = new Date(registerRequestDto.getBirth().getTime() - (long) (new Date(System.currentTimeMillis()).getYear() - 100) *1000*60*60*24*365);
        System.out.println(birth);
        Constellation12 constellation12 = constellation12Repository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(birth,birth);
        newMember.setConstellation12(constellation12);
        try {
            if (newMember != null) {
                memberRepository.save(newMember);
            }
            else {
                throw new BaseException(FAILED_TO_REGISTER);
            }
        }
        catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
        catch (Exception e){
            throw new BaseException(DUPLICATED_ID);
        }
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
    @Transactional
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
