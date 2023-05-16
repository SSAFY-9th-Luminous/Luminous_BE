package com.ssafy.luminous.member.domain;

import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(force = true)
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String memberId;

    @NotNull
    private String memberPassword;

    @NotNull
    private String memberName;

    @NotNull
    private String birth;

    @NotNull
    private Long constellationId;

    public static Member register(RegisterRequestDto registerRequestDto) {
        Member newMember = new Member();
        newMember.memberId = registerRequestDto.getMemberId();
        newMember.memberPassword = registerRequestDto.getMemberPassword();
        newMember.memberName = registerRequestDto.getClass().getName();
        newMember.birth = registerRequestDto.getBirth();
        
        // constellationId 가져오기

        return newMember;
        
    }

}
