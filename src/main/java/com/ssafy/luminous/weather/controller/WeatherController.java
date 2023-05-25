package com.ssafy.luminous.weather.controller;


import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.weather.domain.Weather;
import com.ssafy.luminous.weather.service.WeatherService;
import com.ssafy.luminous.weather.util.api.WeatherUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherUtil weatherUtil;

    @GetMapping("/store")
    public BaseResponse<?> callStoreWeather(){
        try {
            weatherUtil.storeWeather();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("")
    public BaseResponse<List<Weather>> getWeatherList() {
        try {
            return new BaseResponse<>(weatherService.getWeatherList());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }
    }

    @GetMapping("/today/sky")
    public BaseResponse<List<Weather>> getTodayWeatherSkyList() {
        try {
            return new BaseResponse<>(weatherService.getTodayWeatherSkyList());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }
    }
    @GetMapping("/tomorrow/sky")
    public BaseResponse<List<Weather>> getTomorrowWeatherSkyList() {
        try {
            return new BaseResponse<>(weatherService.getTomorrowWeatherSkyList());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }
    }

    @GetMapping("/today/tmp")
    public BaseResponse<List<Weather>> getTodayWeatherTmpList() {
        try {
            return new BaseResponse<>(weatherService.getTodayWeatherTmpList());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }
    }
    @GetMapping("/tomorrow/tmp")
    public BaseResponse<List<Weather>> getTomorrowWeatherTmpList() {
        try {
            return new BaseResponse<>(weatherService.getTomorrowWeatherTmpList());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }
    }
}
