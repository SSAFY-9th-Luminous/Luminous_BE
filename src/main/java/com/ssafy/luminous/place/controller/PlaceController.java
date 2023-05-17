package com.ssafy.luminous.place.controller;

import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("")
    public List<PlaceListResDto> getPlaceList(){
        return placeService.getPlaceList();

    }

//    @PostMapping("")
//    public Long postPlace(@R){
//
//    }
}
