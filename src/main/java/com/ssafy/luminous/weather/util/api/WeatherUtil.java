package com.ssafy.luminous.weather.util.api;

import com.ssafy.luminous.weather.domain.Weather;
import com.ssafy.luminous.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
public class WeatherUtil {

    private final WeatherRepository weatherRepository;

    private @Value("${api.key.weather}") String serviceKey;

    public void storeWeather() throws IOException, ParserConfigurationException, SAXException {
        // 17, 40
        weatherRepository.deleteAll();
        int page = 17;

        // 가져올 데이터 최대 개수
        final int numOfRows = 12;
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyyMMdd");
        String date = format2.format(System.currentTimeMillis());
//        0 서울 127 38
//        1 강원도 128 38
//        2 충청도 127 37
//        3 경상도 129 36
//        4 전라도 127 35
//        5 제주도 127 33
        final int[][] city = {{127, 38},
                                {128,38},
                                {127,37},
                                {129,36},
                                {127, 35},
                                {127, 33}};
        final String[] cities = {"서울", "강원도", "충청도", "경상도", "전라도", "제주도"};
        while (page == 17 || page == 40) {
            for(int j = 0; j< city.length; j++) {

                String urlStr = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst" +
                        "?serviceKey=" + serviceKey +
                        "&pageNo=" + page +
                        "&numOfRows=" + numOfRows +
                        "&dataType=XML" +
                        "&base_date=" + date +
                        "&base_time=0500" +
                        "&nx=" + city[j][1] +
                        "&ny=" + city[j][0];

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
                Document doc = dbBuilder.parse(urlStr);

                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("item");

                for (int i = 0; i < nList.getLength(); i++) {
                    Node nNode = nList.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if(!getTagValue("category", eElement).equals("SKY") && !getTagValue("category", eElement).equals("TMP")){
                            continue;
                        }
                        Weather weather = Weather.builder()
                                .city(cities[j])
                                .category(getTagValue("category", eElement))
                                .fcstDate(getTagValue("fcstDate", eElement))
                                .fcstValue(getTagValue("fcstValue", eElement))
                                .build();

                        weatherRepository.save(weather);
                    }   // if end
                }   // for end
            }
            if (page == 17) {
                page = 40;
            }else if(page == 40){
                break;
            }



        }   // for end

    }

    public String getServiceKey() {
        return serviceKey;
    }

    public static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
