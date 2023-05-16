package com.ssafy.luminous.member.domain;

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
    private Long index;

    @NotNull
    private String memberId;

    @NotNull
    private String memberPassword;

    @NotNull
    private String memberName;

    @NotNull
    private String birth;

    @NotNull
    private String constellationId;

}
