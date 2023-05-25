package com.ssafy.luminous.weather.service;

import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.repository.CampingRepository;
import com.ssafy.luminous.weather.domain.Weather;
import com.ssafy.luminous.weather.dto.WeatherResDto;
import com.ssafy.luminous.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.luminous.camping.util.api.CampingUtil.getTagValue;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    public List<Weather> getWeatherList() {

        return weatherRepository.findAll();
    }

}
