package com.ssafy.luminous.place.controller;

import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.dto.PlaceUpdateReqDto;
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
    public List<PlaceListResDto> getPlaceList(
            // keyword {place, user, desc}
            @RequestParam(value = "category", defaultValue = "place") String category,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
            ){

        return placeService.getPlaceList(category, keyword);

    }

    @GetMapping("/{id}")
    public Place getPlace(@PathVariable Long id){
        return placeService.getPlace(id);
    }

    @PostMapping("")
    public Place postPlace(@RequestBody PlacePostReqDto placePostReqDto){
        // todo jwt member Id
        return placeService.postPlace(placePostReqDto,1L);
    }
    @DeleteMapping("/{id}")
    public Boolean deletePlace(@PathVariable Long id){
        placeService.deletePlace(id);
        return true;
    }

    @PutMapping("/{id}")
    public Place updatePlace(@PathVariable Long id, @RequestBody PlaceUpdateReqDto placeUpdateReqDto){
        return placeService.updatePlace(id, placeUpdateReqDto);
    }


}
