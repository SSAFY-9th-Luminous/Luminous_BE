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

    private Boolean isObservable;

    private Boolean hasExhibition;

    private Boolean hasLodgment;

    private Boolean hasPlaintarium;

    private String homePage;

    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    private Double rate;




}
