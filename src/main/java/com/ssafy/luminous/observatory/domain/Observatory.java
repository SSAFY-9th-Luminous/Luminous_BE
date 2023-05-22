package com.ssafy.luminous.observatory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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

    @Column(columnDefinition = "double default 0")
    private Double rate;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;





}
