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
    @Column(name="member_id")
    private Long id;

    @NotNull
    private String userId;

    @NotNull
    private String userPassword;

    @NotNull
    private String userName;

    @NotNull
    private String birth;

    @NotNull
    private String constellationId;

}
