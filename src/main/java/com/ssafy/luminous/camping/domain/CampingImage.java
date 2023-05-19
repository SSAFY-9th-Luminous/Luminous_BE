package com.ssafy.luminous.camping.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampingImage {

    private Long id;

    @Column(nullable = false)
    private String serialNum;

    @Column(nullable = false)
    private String imageUri;


}
