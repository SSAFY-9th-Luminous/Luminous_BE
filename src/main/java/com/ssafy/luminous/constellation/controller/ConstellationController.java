package com.ssafy.luminous.constellation.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.constellation.service.ConstellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constellations")
@RequiredArgsConstructor
public class ConstellationController {

    private final ConstellationService constellationService;

    @GetMapping("/list")
    public BaseResponse<?> getConstellationList(){
        try {
            return new BaseResponse<>(constellationService.getConstellationList());
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
