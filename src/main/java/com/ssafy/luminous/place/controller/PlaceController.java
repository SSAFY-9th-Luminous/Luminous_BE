package com.ssafy.luminous.place.controller;

import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public Place postPlace(@RequestBody PlacePostReqDto placePostReqDto){
        // todo jwt member Id
        return placeService.postPlace(placePostReqDto,1L);
    }


}
