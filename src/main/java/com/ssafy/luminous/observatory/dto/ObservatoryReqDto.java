package com.ssafy.luminous.observatory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ObservatoryReqDto {
    private Long id;

    private String observatoryName;

    private Integer isObservable;

    private Integer hasExhibition;

    private Integer hasLodgment;

    private Integer hasPlaintarium;

    private String homePage;

    private String phoneNumber;

    private String address;

    @Builder.Default
    private Double rate= 0.0;
}
