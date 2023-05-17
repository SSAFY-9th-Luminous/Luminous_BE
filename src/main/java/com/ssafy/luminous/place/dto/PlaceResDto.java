package com.ssafy.luminous.place.dto;

import com.ssafy.luminous.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
public class PlaceResDto {

    private Long id;

    private String placeName;

    private String placeDescription;

    private Date visitedDate;

    private Date createdDate;
    private Date lastModifiedDate;


    private String img;

    private Double latitude;

    private Double longitude;

    private String address;

    private Double rate = (double) 0;

    private Integer hit = 0;

    private Member member;


}
