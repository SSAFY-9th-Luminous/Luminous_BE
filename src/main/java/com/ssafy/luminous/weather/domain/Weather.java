package com.ssafy.luminous.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int city_id;

    private String category;

    @Column(nullable = false)
    private String fcstDate;

    @Column(nullable = false)
    private String fcstValue;

}
