package com.ssafy.luminous.camping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampingListResponseDto {

    private String doName;

    private String siGunGuName;

    private String campingId;

    private String campingName;

    private String lineIntro;

    private Integer hit;

}
