package com.ssafy.luminous.observatory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObservatoryRateRequestDto {
    private long id;

    private int rate;
}
