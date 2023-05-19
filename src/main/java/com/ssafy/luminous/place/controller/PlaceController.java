package com.ssafy.luminous.place.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.dto.PlaceUpdateReqDto;
import com.ssafy.luminous.place.service.PlaceService;
import com.ssafy.luminous.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ssafy.luminous.config.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;
    private final JwtService jwtService;

    @GetMapping("")
    public BaseResponse<List> getPlaceList(
            // keyword {place, user, desc}
            @RequestParam(value = "category", defaultValue = "place") String category,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        try {
            return new BaseResponse<>(placeService.getPlaceList(category, keyword));

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }

    @GetMapping("/{id}")
    public BaseResponse<Place> getPlace(@PathVariable Long id) {
        try {
            placeService.updateHit(id);
            return new BaseResponse<>(placeService.getPlace(id));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    @PostMapping("")
    public BaseResponse<Place> postPlace(HttpServletRequest request, @RequestBody PlacePostReqDto placePostReqDto) {
//        Long memberId = jwtService.getIdFromToken(request);
//        return placeService.postPlace(placePostReqDto,memberId);
        try {

            return new BaseResponse<>(placeService.postPlace(placePostReqDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> deletePlace(HttpServletRequest request, @PathVariable Long id) {
//        Long memberId = jwtService.getIdFromToken(request);
//        placeService.deletePlace(id,memberId);
        try {
            placeService.deletePlace(id);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PutMapping("/{id}")
    public BaseResponse<Place> updatePlace(HttpServletRequest request, @PathVariable Long id, @RequestBody PlaceUpdateReqDto placeUpdateReqDto) {
//        Long memberId = jwtService.getIdFromToken(request);
//        return placeService.updatePlace(id, placeUpdateReqDto,memberId);
        try {
            return new BaseResponse<>(placeService.updatePlace(id, placeUpdateReqDto));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
