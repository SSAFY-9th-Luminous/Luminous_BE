package com.ssafy.luminous.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequestDto {

    private Long id;

    private String memberId;

    private String memberPassword;

    private String memberName;

    private Date birth;

}
