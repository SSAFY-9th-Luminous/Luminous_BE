package com.ssafy.luminous.place.dto;

import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.place.domain.Place;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class PlaceListResDto {
    private Long id;

    private String placeName;

    private String placeDescription;

    private Date createdDate;
    //여기부터
    private Double latitude;

    private Double longitude;

    private String address;
    //여기까지 추가했음 23-05-22
    private Double rate;

    private Integer hit;

    private Member member;

}
