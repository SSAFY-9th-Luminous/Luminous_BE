package com.ssafy.luminous.member.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(force = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String constellationId;

}
