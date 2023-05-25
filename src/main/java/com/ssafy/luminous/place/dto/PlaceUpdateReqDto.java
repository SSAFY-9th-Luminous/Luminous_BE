package com.ssafy.luminous.place.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateReqDto {
    private Long id;

    private String placeName;

    private String placeDescription;

    private Date visitedDate;

    private transient MultipartFile img;

    private String url;

    private Double latitude;

    private Double longitude;

    private String address;

    private Double rate = (double) 0;
}
