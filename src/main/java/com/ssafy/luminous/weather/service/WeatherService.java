package com.ssafy.luminous.weather.service;

import com.ssafy.luminous.weather.domain.Weather;
import com.ssafy.luminous.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    public List<Weather> getWeatherList() {

        return weatherRepository.findAll();
    }

    public List<Weather> getTodayWeatherSkyList(){
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyyMMdd");
        String date = format2.format(System.currentTimeMillis());
        return weatherRepository.findByFcstDateAndCategory(date, "SKY");
    }

    public List<Weather> getTodayWeatherTmpList(){
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyyMMdd");
        String date = format2.format(System.currentTimeMillis());
        return weatherRepository.findByFcstDateAndCategory(date, "TMP");
    }

    public List<Weather> getTomorrowWeatherSkyList(){
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyyMMdd");
        String date = format2.format(System.currentTimeMillis()+1000*60*60*24);

        return weatherRepository.findByFcstDateAndCategory(date, "SKY");
    }

    public List<Weather> getTomorrowWeatherTmpList(){
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyyMMdd");
        String date = format2.format(System.currentTimeMillis()+1000*60*60*24);

        return weatherRepository.findByFcstDateAndCategory(date, "TMP");
    }


}
