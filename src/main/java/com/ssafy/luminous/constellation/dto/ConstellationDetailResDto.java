package com.ssafy.luminous.constellation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstellationDetailResDto {

    private Long id;
    private String contentsName;
    private String scientificName;
    private String engName;
    private LocalDateTime meridianDateTime;
    private Integer amountOfStars;
    private String size;
    private String location;
    private String contentsDescription;
    private String img;
}
