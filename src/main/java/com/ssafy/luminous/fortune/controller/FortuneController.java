package com.ssafy.luminous.fortune.controller;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponse;
import com.ssafy.luminous.fortune.domain.Fortune;
import com.ssafy.luminous.fortune.dto.FortuneReqDto;
import com.ssafy.luminous.fortune.dto.TodayFortuneResDto;
import com.ssafy.luminous.fortune.service.FortuneService;
import com.ssafy.luminous.util.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpResponse;

import static com.ssafy.luminous.config.BaseResponseStatus.RESPONSE_ERROR;
import static com.ssafy.luminous.config.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/fortunes")
@RequiredArgsConstructor
public class FortuneController {

    private final FortuneService fortuneService;
    private final JwtService jwtService;

    @GetMapping()
    public BaseResponse<?> getTodayFortune(HttpServletRequest request){
        try {
            Long memberId = jwtService.getIdFromToken(request);
            TodayFortuneResDto todayFortune = fortuneService.getTodayFortune(memberId);

            return new BaseResponse<>(todayFortune);
        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/list")
    public BaseResponse<?> getFortuneList() {
        try {
            return new BaseResponse<>(fortuneService.getTodayFortuneList());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());

        }
    }

    @PostMapping("")
    public BaseResponse<?> createFortune() {
        try {
            fortuneService.createFortune();
            return new BaseResponse<>(SUCCESS);
        }
        catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
