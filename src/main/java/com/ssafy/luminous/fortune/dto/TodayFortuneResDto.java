package com.ssafy.luminous.fortune.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TodayFortuneResDto {
    private String name;
    private String description;
}
