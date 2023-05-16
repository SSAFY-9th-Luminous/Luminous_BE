package com.ssafy.luminous.member.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor(force = true)
public class RegisterRequestDto {
    @NotNull
    private String memberId;

    @NotNull
    private String memberPassword;

    @NotNull
    private String memberName;

    @NotNull
    private Date birth;
}
