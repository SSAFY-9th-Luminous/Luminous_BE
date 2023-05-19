package com.ssafy.luminous.camping.controller;

import com.ssafy.luminous.camping.domain.CampingImage;
import com.ssafy.luminous.camping.service.CampingService;
import com.ssafy.luminous.camping.util.api.CampingUtil;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/camping")
public class CampingController {

    private final CampingService campingService;
    private final CampingUtil campingUtil;

    @GetMapping("/")
    public BaseResponse<Object> callStoreCamping() {
        try {
            campingUtil.storeCamping();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

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



}
