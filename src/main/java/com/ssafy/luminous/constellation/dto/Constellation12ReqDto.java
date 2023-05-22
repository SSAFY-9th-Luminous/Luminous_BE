package com.ssafy.luminous.constellation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Constellation12ReqDto {
    private Long id;
    private Date startDate;
    private Date endDate;
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
