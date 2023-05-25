package com.ssafy.luminous.weather.repository;

import com.ssafy.luminous.weather.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findAll();

    List<Weather> findByFcstDate(String date);

    List<Weather> findByFcstDateAndCategory(String date, String tmp);
}
