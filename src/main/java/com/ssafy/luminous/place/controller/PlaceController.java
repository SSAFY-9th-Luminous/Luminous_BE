package com.ssafy.luminous.place.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.dto.PlaceUpdateReqDto;
import com.ssafy.luminous.place.service.PlaceService;
import com.ssafy.luminous.util.gpt.ChatGPTService;
import com.ssafy.luminous.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final JwtService jwtService;

    @GetMapping("")
    public List<PlaceListResDto> getPlaceList(
            // keyword {place, user, desc}
            @RequestParam(value = "category", defaultValue = "place") String category,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
            ){

        return placeService.getPlaceList(category, keyword);

    }

    @GetMapping("/{id}")
    public BaseResponse<Place> getPlace(@PathVariable Long id){
        try {
            return new BaseResponse<>(placeService.getPlace(id)) ;
        }
        catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

    @PostMapping("")
    public Place postPlace(HttpServletRequest request , @RequestBody PlacePostReqDto placePostReqDto){
//        Long memberId = jwtService.getIdFromToken(request);
//        return placeService.postPlace(placePostReqDto,memberId);
        return placeService.postPlace(placePostReqDto);
    }
    @DeleteMapping("/{id}")
    public Boolean deletePlace(HttpServletRequest request ,@PathVariable Long id){
//        Long memberId = jwtService.getIdFromToken(request);
//        placeService.deletePlace(id,memberId);
        placeService.deletePlace(id);
        return true;
    }

    @PutMapping("/{id}")
    public Place updatePlace(HttpServletRequest request ,@PathVariable Long id, @RequestBody PlaceUpdateReqDto placeUpdateReqDto){
//        Long memberId = jwtService.getIdFromToken(request);
//        return placeService.updatePlace(id, placeUpdateReqDto,memberId);
        return placeService.updatePlace(id, placeUpdateReqDto);
    }


}
