package com.ssafy.luminous.camping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampingListResponseDto {

    private Long id;

    private String doName;

    private String address;

    private String campingId;

    private String campingName;

    private String lineIntro;

    private String imageUrl;

}
