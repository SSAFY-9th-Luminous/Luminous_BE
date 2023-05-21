package com.ssafy.luminous.observatory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ObservatoryListResponseDto {

    private Long id;

    private String siDoName;

    private String observatoryName;

    private String address;

}
