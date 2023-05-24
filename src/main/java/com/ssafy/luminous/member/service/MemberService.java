package com.ssafy.luminous.member.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.repository.Constellation12Repository;
import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.domain.MemberStatus;
import com.ssafy.luminous.member.dto.LoginRequestDto;
import com.ssafy.luminous.member.dto.MemberDetailReqDto;
import com.ssafy.luminous.member.dto.MemberUpdateRequestDto;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.ssafy.luminous.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

import static com.ssafy.luminous.config.BaseResponseStatus.*;

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

        Constellation12 constellation12 = mappingConstellation12(registerRequestDto.getBirth());
        newMember.setConstellation12(constellation12);

        try {
            if (newMember != null) {
                memberRepository.save(newMember);
            } else {
                throw new BaseException(FAILED_TO_REGISTER);
            }
        } catch (BaseException e) {
            throw new BaseException(e.getStatus());
        } catch (Exception e) {
            throw new BaseException(DUPLICATED_ID);
        }
    }

    private Constellation12 mappingConstellation12(Date date) {
        // 2000년으로 바운딩
        Date boundedDate = new Date(100, date.getMonth(), date.getDate());
        Optional<Constellation12> constellation12 = constellation12Repository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(boundedDate, boundedDate);
        if (constellation12.isEmpty())
            constellation12 = constellation12Repository.findById(10L);

        return constellation12.get();

    }

    // 로그인
    public Member login(LoginRequestDto loginRequestDto) throws BaseException {
        Optional<Member> member = memberRepository.findByMemberId(loginRequestDto.getMemberId());

        // 아이디가 다를 경우
        if (member.isEmpty()) {
            throw new BaseException(NOT_MATCHED_ID);
        }
        // 비밀번호 비교
        if (!validatePassword(member.get().getMemberPassword(), loginRequestDto.getMemberPassword())) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        if (member.get().getMemberStatus() == MemberStatus.DELETED) {
            throw new BaseException(DELETED_USER);
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

        Constellation12 constellation12 = mappingConstellation12(memberUpdateRequestDto.getBirth());
        member.get().update(memberUpdateRequestDto);
        member.get().setConstellation12(constellation12);
        return member.get();
    }

    // 회원탈퇴
    @Transactional
    public void deleteMember(Long id) throws BaseException {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new BaseException(NOT_MATCHED_ID);
        }
        member.get().delete();

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
    public MemberDetailReqDto getMemberDetail(Long id) throws BaseException {
        try {
            Member member = memberRepository.findById(id).get();

            MemberDetailReqDto detailReqDto = MemberDetailReqDto
                    .builder()
                    .id(member.getId())
                    .memberId(member.getMemberId())
                    .memberName(member.getMemberName())
                    .birth(member.getBirth())
                    .constellation12Id(member.getConstellation12().getId())
                    .constellation12Name(member.getConstellation12().getConstellationDetail().getContentsName())
                    .constellation12Img(member.getConstellation12().getImg())
                    .build();
            return detailReqDto;
        } catch (Exception e) {
            throw new BaseException(NOT_MATCHED_ID);
        }

    }
}
