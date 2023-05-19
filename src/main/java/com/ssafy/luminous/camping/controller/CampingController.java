package com.ssafy.luminous.camping.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.dto.CampingListResponseDto;
import com.ssafy.luminous.camping.service.CampingService;
import com.ssafy.luminous.camping.util.api.CampingUtil;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/map")
    public BaseResponse<List<Camping>> getCampingListToMap(
            @RequestParam(value = "region", defaultValue = "") String region
    ) {
        return new BaseResponse<>(campingService.findByDoNameContaining(region));
    }

//    @GetMapping("/location")
//    public BaseResponse<List<Camping>> getCampingByLocation(@RequestParam("pageNumber") int pageNumber, @RequestParam("")) {
//
//
//    }





}
