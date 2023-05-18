package com.ssafy.luminous.observatory.controller;

import com.ssafy.luminous.observatory.domain.Observatory;
import com.ssafy.luminous.observatory.service.ObservatoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/observatorys")
public class ObservatoryController {

    private final ObservatoryService observatoryService;

    @GetMapping("")
    public List<Observatory> getPlaceList(
            // keyword {address}
            @RequestParam(value = "address", defaultValue = "") String keyword
    ){

        return observatoryService.getObservatoryList(keyword);
    }
}
