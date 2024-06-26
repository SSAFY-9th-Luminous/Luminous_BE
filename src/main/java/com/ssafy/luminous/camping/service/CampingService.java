package com.ssafy.luminous.camping.service;

import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.dto.CampingDetailResponseDto;
import com.ssafy.luminous.camping.dto.CampingListByLocationRequestDto;
import com.ssafy.luminous.camping.dto.CampingListResponseDto;
import com.ssafy.luminous.camping.dto.CampingRateRequestDto;
import com.ssafy.luminous.camping.repository.CampingRepository;
import com.ssafy.luminous.camping.util.api.CampingUtil;
import com.ssafy.luminous.config.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Optional;

import static com.ssafy.luminous.camping.util.api.CampingUtil.getTagValue;
import static com.ssafy.luminous.config.BaseResponseStatus.NOT_MATCHED_CAMPING;

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

    // 캠핑장 목록 반환 By 현재위치
    public List<CampingListResponseDto> getCampingListByLocation(double latitude, double longitude) throws ParserConfigurationException, IOException, SAXException {
        List<CampingListResponseDto> campingListResponseDtoList = new ArrayList<>();

        int page = 1;

        double mapX = longitude;
        double mapY = latitude;

        // 가져올 최대 데이터 개수
        final int numOfRows = 15;
        // 5km
        final int radius = 20000;

        String urlStr = "https://apis.data.go.kr/B551011/GoCamping/locationBasedList" +
                "?serviceKey=" + serviceKey +
                "&numOfRows=" + numOfRows +
                "&pageNo=" + page +
                "&MobileOS=ETC" +
                "&MobileApp=Luminous" +
                "&mapX=" + mapX +
                "&mapY=" + mapY +
                "&radius=" + radius;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document doc = dbBuilder.parse(urlStr);

        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("item");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                // 사진 있는 캠핑장만 가져오기
                if (getTagValue("firstImageUrl", eElement) == null) continue;

                campingListResponseDtoList.add(CampingListResponseDto.builder()
                        .id(new Long(i + 1))
                        .doName(getTagValue("doNm", eElement))
                        .address(getTagValue("addr1", eElement))
                        .campingId(getTagValue("contentId", eElement))
                        .campingName(getTagValue("facltNm", eElement))
                        .lineIntro(getTagValue("lineIntro", eElement))
                        .imageUrl(getTagValue("firstImageUrl", eElement))
                        .rate(0.0)
                        .count(0)
                        .build());
            }   // if end
        }   // for end

        return campingListResponseDtoList;
    }

    // 캠핑 목록 반환(CampingListResponseDto)
    @Transactional(readOnly = true)
    public List<CampingListResponseDto> getCampingList(String region)  {
        List<Camping> campingList = campingRepository.findByDoNameContaining(region);

        List<CampingListResponseDto> campingListResponseDtoList = new ArrayList<>();

        for (Camping camping : campingList) {
            campingListResponseDtoList.add(CampingListResponseDto.builder()
                    .id(camping.getId())
                    .doName(camping.getDoName())
                    .address(camping.getAddress())
                    .campingId(camping.getCampingId())
                    .campingName(camping.getCampingName())
                    .lineIntro(camping.getLineIntro())
                    .imageUrl(camping.getImageUrl())
                    .count(camping.getCount())
                    .rate(camping.getRate())
                    .build());
        }

        return campingListResponseDtoList;
    }

    public List<Camping> findByDoNameContaining(String doName) {
        return campingRepository.findByDoNameContaining(doName);
    }

    public CampingDetailResponseDto getCamping(Long id) throws BaseException, ParserConfigurationException, IOException, SAXException {
        Optional<Camping> camping = campingRepository.findById(id);

        if (camping.isEmpty()) {
            throw new BaseException(NOT_MATCHED_CAMPING);
        }

        CampingDetailResponseDto campingDetailResponseDto = CampingDetailResponseDto.builder()
                .camping(camping.get())
                .campingImageList(getCampingImages(camping.get().getCampingId()))
                .build();


        return campingDetailResponseDto;
    }

    // 평점 추가
    @Transactional
    public Camping rateCamping(CampingRateRequestDto campingRateRequestDto) throws BaseException {
        long id = campingRateRequestDto.getId();
        int rate = campingRateRequestDto.getRate();

        Optional<Camping> camping = campingRepository.findById(id);

        if (camping.isEmpty()) {
            throw new BaseException(NOT_MATCHED_CAMPING);
        }

        int campingCount = camping.get().getCount();
        Double campingRate = camping.get().getRate();

        campingRate = ((campingRate * campingCount) + rate) / ++campingCount;

        camping.get().setCount(campingCount);
        camping.get().setRate(campingRate);

        return camping.get();
    }

}
