package com.ssafy.luminous.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateReqDto {
    private Long id;

    private String placeName;

    private String placeDescription;

    private Date visitedDate;

    private Blob img;

    private Double latitude;

    private Double longitude;

    private String address;

    private Double rate = (double) 0;
}
