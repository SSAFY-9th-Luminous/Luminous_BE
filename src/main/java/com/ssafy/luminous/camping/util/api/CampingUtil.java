package com.ssafy.luminous.camping.util.api;

import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.repository.CampingRepository;
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
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CampingUtil {

    private final CampingRepository campingRepository;

    private @Value("${api.key.camping}") String serviceKey;

    public void storeCamping() throws IOException, ParserConfigurationException, SAXException {
        int page = 1;

        // 가져올 데이터 최대 개수
        final int numOfRows = 100;
        final int numOfPages = 10;

        while (true) {

            String urlStr = "https://apis.data.go.kr/B551011/GoCamping/basedList" +
                    "?serviceKey=" + serviceKey +
                    "&numOfRows=" + numOfRows +
                    "&pageNo=" + page +
                    "&MobileOS=ETC" +
                    "&MobileApp=Luminous";

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(urlStr);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Camping camping = Camping.builder()
                            .campingId(getTagValue("contentId", eElement))
                            .campingName(getTagValue("facltNm", eElement))
                            .lineIntro(getTagValue("lineIntro", eElement))
                            .intro(getTagValue("intro", eElement))
                            .doName(getTagValue("doNm", eElement))
                            .siGunGuName(getTagValue("sigunguNm", eElement))
                            .address(getTagValue("addr1", eElement))
                            .latitude(Double.valueOf(getTagValue("mapY", eElement)))
                            .longitude(Double.valueOf(getTagValue("mapX", eElement)))
                            .tel(getTagValue("tel", eElement))
                            .homepage(getTagValue("homepage", eElement))
                            .glamping(getTagValue("glampInnerFclty", eElement))
                            .caravan(getTagValue("caravInnerFclty", eElement))
                            .facility(getTagValue("sbrsCl", eElement))
                            .theme(getTagValue("themaEnvrnCl", eElement))
                            .imageUrl(getTagValue("firstImageUrl", eElement))
                            .rate(0.0)
                            .count(0)
                            .build();

                    campingRepository.save(camping);
                }   // if end
            }   // for end

            page++;
            if (page > numOfPages) {
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
