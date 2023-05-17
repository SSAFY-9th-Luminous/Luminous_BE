package com.ssafy.luminous.member.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class LoginRequestDto {

    @NotNull
    private String memberId;

    @NotNull
    private String memberPassword;


}
