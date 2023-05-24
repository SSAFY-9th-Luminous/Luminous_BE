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
import com.ssafy.luminous.util.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    public BaseResponse<?> postPlace(HttpServletRequest request,
                                         @ModelAttribute PlacePostReqDto placePostReqDto)
        {
        try {
            Long memberId = jwtService.getIdFromToken(request);
            placeService.postPlace(placePostReqDto, memberId);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> deletePlace(HttpServletRequest request,
                                       @PathVariable Long id) {
        try {
            Long memberId = jwtService.getIdFromToken(request);
            placeService.deletePlace(id, memberId);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PutMapping("/{id}")
    public BaseResponse<Place> updatePlace(HttpServletRequest request,
                                           @PathVariable Long id,
                                           @ModelAttribute PlaceUpdateReqDto placeUpdateReqDto) {
        try {
            Long memberId = jwtService.getIdFromToken(request);
            placeService.updatePlace(id, placeUpdateReqDto, memberId);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
