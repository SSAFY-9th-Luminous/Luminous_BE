package com.ssafy.luminous.place.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String placeName;

    private String placeDescription;

    private Date visitedDate;

    private Date createdDate;
    private Date lastModifiedDate;


    private Blob img;

    private Double latitude;

    private Double longitude;

    private String address;

    private Double rate = (double) 0;

    private Integer hit = 0;



}
