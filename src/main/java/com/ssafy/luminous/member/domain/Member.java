package com.ssafy.luminous.member.domain;

import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.member.dto.RegisterRequestDto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberId;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private Date birth;

    private Long constellationId;

    @OneToOne
    private Constellation12 constellation12;

}
