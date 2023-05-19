package com.ssafy.luminous.place.dto;

import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.place.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlacePostReqDto {

    private String placeName;

    private String placeDescription;

    private Date visitedDate;

    private String img;

    private Double latitude;

    private Double longitude;

    private String address;

    private Double rate = (double) 0;

    private Long memberId;

    public Place toEntity(Long memberId){
        return Place.builder()
                .placeName(placeName)
                .placeDescription(placeDescription)
                .visitedDate(visitedDate)
                .img(img)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .createdDate(new Date(System.currentTimeMillis()))
                .lastModifiedDate(new Date(System.currentTimeMillis()))
                .member(Member.builder().id(memberId).build())
                .rate(rate)
                .hit(0)
                .build()
                ;


    }

}
