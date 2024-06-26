package com.ssafy.luminous.camping.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.dto.CampingDetailResponseDto;
import com.ssafy.luminous.camping.dto.CampingListByLocationRequestDto;
import com.ssafy.luminous.camping.dto.CampingListResponseDto;
import com.ssafy.luminous.camping.dto.CampingRateRequestDto;
import com.ssafy.luminous.camping.service.CampingService;
import com.ssafy.luminous.camping.util.api.CampingUtil;
import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/camping")
public class CampingController {

    private final CampingService campingService;
    private final CampingUtil campingUtil;

    // DB에 Camping 데이터 저장
    @GetMapping("/store")
    public BaseResponse<Object> callStoreCamping() {
        try {
            campingUtil.storeCamping();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 캠핑장 이미지 반환
    @GetMapping("/image/{campingId}")
    public BaseResponse<List<CampingImage>> getCampingImages(@PathVariable("campingId") String campingId) {
        List<CampingImage> campingImageList;
        try {
            campingImageList = campingService.getCampingImages(campingId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }

        return new BaseResponse<>(campingImageList);
    }
    
    // 캠핑장 리스트 반환
    @GetMapping("")
    public BaseResponse<List<CampingListResponseDto>> getCampingList(
            @RequestParam(value = "region", defaultValue = "") String region
            ) {

        return new BaseResponse<>(campingService.getCampingList(region));
    }

    // 지도로 캠핑장 리스트 반환
    @GetMapping("/map")
    public BaseResponse<List<Camping>> getCampingListToMap(
            @RequestParam(value = "region", defaultValue = "") String region
    ) {
        return new BaseResponse<>(campingService.findByDoNameContaining(region));
    }

    // 캠핑 디테일 정보 반환(Camping + images)
    @GetMapping("/{id}")
    public BaseResponse<CampingDetailResponseDto> getCamping(@PathVariable Long id) {
        try {
            return new BaseResponse<>(campingService.getCamping(id));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }

    }

    // 캠핑 평점 반영
    @PostMapping("/rate")
    public BaseResponse<Object> rateCamping(
            @RequestBody CampingRateRequestDto campingRateRequestDto
    ) {

        try {
            return new BaseResponse<>(campingService.rateCamping(campingRateRequestDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }

    // 캠핑 리스트 반환 By 현재위치
    @GetMapping("/location")
    public BaseResponse<List<CampingListResponseDto>> getCampingListByLocation(
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude
    ) {
        try {
            return new BaseResponse<>(campingService.getCampingListByLocation(latitude, longitude));
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.API_ERROR);
        }
    }


}
