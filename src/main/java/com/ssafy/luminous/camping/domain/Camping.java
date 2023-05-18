package com.ssafy.luminous.camping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Camping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String campingId;

    @Column(nullable = false)
    private String campingName;

    private String lineIntro;

    @Column(length = 2000)
    private String intro;

    private String doName;

    private String siGunGuName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;       // 위도

    @Column(nullable = false)
    private Double longitude;       // 경도

    private String tel;

    private String homepage;

    private String glamping;

    private String caravan;

}
