package com.ssafy.luminous.member.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class RegisterRequestDto {

    private String memberId;

    private String memberPassword;

    private String memberName;

    private Date birth;
}
