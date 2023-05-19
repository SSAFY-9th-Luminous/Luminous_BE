package com.ssafy.luminous.camping.service;

import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.dto.CampingListResponseDto;
import com.ssafy.luminous.camping.repository.CampingRepository;
import com.ssafy.luminous.camping.util.api.CampingUtil;
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
public class CampingService {

    private @Value("${api.key.camping}") String serviceKey;

    private final CampingRepository campingRepository;

    public List<CampingImage> getCampingImages(String campingId) throws ParserConfigurationException, IOException, SAXException {
        List<CampingImage> CampingImageList = new ArrayList<>();

        int page = 1;

        // 가져올 최대 데이터 개수
        final int numOfRows = 30;

        String urlStr = "https://apis.data.go.kr/B551011/GoCamping/imageList" +
                "?serviceKey=" + serviceKey +
                "&numOfRows=" + numOfRows +
                "&pageNo=" + page +
                "&MobileOS=ETC" +
                "&MobileApp=Luminous" +
                "&contentId=" + campingId;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document doc = dbBuilder.parse(urlStr);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                CampingImage campingImage = CampingImage.builder()
                        .id(new Long(i+1))
                        .serialNum(getTagValue("serialnum", eElement))
                        .imageUri(getTagValue("imageUrl", eElement))
                        .build();

                CampingImageList.add(campingImage);
            }   // if end
        }   // for end

        return CampingImageList;
    }

    public List<CampingListResponseDto> getCampingList(String region)  {
        List<Camping> campings = campingRepository.findByDoNameContaining(region);

        List<CampingListResponseDto> campingListResponseDtoList = new ArrayList<>();

        for (Camping camping : campings) {
            campingListResponseDtoList.add(CampingListResponseDto.builder()
                    .doName(camping.getDoName())
                    .siGunGuName(camping.getSiGunGuName())
                    .campingId(camping.getCampingId())
                    .campingName(camping.getCampingName())
                    .lineIntro(camping.getLineIntro())
                    .hit(0)
                    .build());
        }

        return campingListResponseDtoList;
    }

    public List<Camping> findByDoNameContaining(String doName) {
        return campingRepository.findByDoNameContaining(doName);
    }




//    public List<CampingListResponseDto> getCampingByLocation(int pageNumber) throws ParserConfigurationException, IOException, SAXException {
//        List<CampingListResponseDto> campingList = new ArrayList<>();
//
//        // 가져올 최대 데이터 개수
//        final int numOfRows = 10;
//
//        128.6142847&mapY=36.0345423
//
//        String urlStr = "https://apis.data.go.kr/B551011/GoCamping/locationBasedList" +
//                "?serviceKey=" + serviceKey +
//                "&numOfRows=" + numOfRows +
//                "&pageNo=" + pageNumber +
//                "&MobileOS=ETC" +
//                "&MobileApp=Luminous" +
//                "&mapX=" + campingId +
//                "&mapX=" + campingId +
//                "&radius=2000";
//
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dbBuilder.parse(urlStr);
//
//        doc.getDocumentElement().normalize();
//        NodeList nList = doc.getElementsByTagName("item");
//
//        for (int i = 0; i < nList.getLength(); i++) {
//            Node nNode = nList.item(i);
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                CampingImage campingImage = CampingImage.builder()
//                        .id(new Long(i+1))
//                        .serialNum(getTagValue("serialnum", eElement))
//                        .imageUri(getTagValue("imageUrl", eElement))
//                        .build();
//
//                CampingImageList.add(campingImage);
//            }   // if end
//        }   // for end
//
//        return CampingImageList;
//
//    }
}
