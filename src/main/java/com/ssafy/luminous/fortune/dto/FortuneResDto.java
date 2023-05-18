package com.ssafy.luminous.fortune.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FortuneResDto {
    private Long contentsId;
    private String contentsName;
    private String description;

}
