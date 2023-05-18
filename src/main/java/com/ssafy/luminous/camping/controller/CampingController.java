package com.ssafy.luminous.camping.controller;

import com.ssafy.luminous.camping.util.api.CampingUtil;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CampingController {

    private final CampingUtil campingUtil;

    @GetMapping("/camping")
    public BaseResponse<Object> callStoreCamping() {
        try {
            campingUtil.storeCamping();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);
        }

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }



}
