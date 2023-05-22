package com.ssafy.luminous.constellation.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.constellation.service.Constellation12Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constellations-12")
@RequiredArgsConstructor
public class Constellation12Controller {
    private final Constellation12Service constellation12Service;


    @GetMapping("/list")
    public BaseResponse<?> getConstellationList(){
        try {
            return new BaseResponse<>(constellation12Service.getConstellation12List());
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

}
