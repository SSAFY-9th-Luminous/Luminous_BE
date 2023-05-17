package com.ssafy.luminous.place.dto;

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
    private Double rate;

    private Integer hit;

    private Long memberId;

}
