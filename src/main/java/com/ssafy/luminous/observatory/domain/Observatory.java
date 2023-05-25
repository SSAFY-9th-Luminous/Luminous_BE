package com.ssafy.luminous.observatory.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Observatory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String observatoryName;

    @Column(nullable = false)
    private Integer isObservable;

    @Column(nullable = false)
    private Integer hasExhibition;

    @Column(nullable = false)
    private Integer hasLodgement;

    @Column(nullable = false)
    private Integer hasPlanetarium;

    private String homePage;

    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int count;

    @Column(columnDefinition = "double default 0")
    private Double rate;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private String imageUrl;


}
