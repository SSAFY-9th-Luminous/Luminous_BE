package com.ssafy.luminous.member.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailReqDto {
    private Long id;
    private String memberId;
    private String memberName;
    private Date birth;
    private Long constellation12Id;
    private String constellation12Name;

}
