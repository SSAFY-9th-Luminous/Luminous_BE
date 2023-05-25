package com.ssafy.luminous.weather.dto;

import com.ssafy.luminous.weather.domain.Weather;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResDto {
    private Weather weather;
}
