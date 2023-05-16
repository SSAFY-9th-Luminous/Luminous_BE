package com.ssafy.luminous.member.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotNull
    private String memberId;

    @NotNull
    private String memberPassword;

    @NotNull
    private String memberName;

    @NotNull
    private String birth;
}
