package com.ssafy.luminous.observatory.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.observatory.domain.Observatory;
import com.ssafy.luminous.observatory.dto.ObservatoryListResponseDto;
import com.ssafy.luminous.observatory.service.ObservatoryService;
import com.ssafy.luminous.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/observatory")
public class ObservatoryController {

    private final ObservatoryService observatoryService;

    @GetMapping("/map")
    public BaseResponse<List<Observatory>> getObservatoryListToMap(
            // keyword {address}
            @RequestParam(value = "address", defaultValue = "") String keyword
    ){

        return new BaseResponse<>(observatoryService.getObservatoryListToMap(keyword));
    }

    @GetMapping("")
    public BaseResponse<List<ObservatoryListResponseDto>> getObservatoryList(
            @RequestParam(value = "address", defaultValue = "") String keyword
    ) {

        return new BaseResponse<>(observatoryService.getObservatoryList(keyword));
    }

    @GetMapping("/{id}")
    public BaseResponse<Observatory> getPlace(@PathVariable Long id) {
        try {
            return new BaseResponse<>(observatoryService.getObservatory(id));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }
}
