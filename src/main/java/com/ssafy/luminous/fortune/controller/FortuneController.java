package com.ssafy.luminous.fortune.controller;

import com.ssafy.luminous.fortune.domain.Fortune;
import com.ssafy.luminous.fortune.dto.FortuneReqDto;
import com.ssafy.luminous.fortune.service.FortuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/fortunes")
@RequiredArgsConstructor
public class FortuneController {

    private final FortuneService fortuneService;


    @GetMapping("")
    public Fortune getFortune(@RequestBody FortuneReqDto fortuneReqDto){
        return fortuneService.getFortune(fortuneReqDto.getId());
    }

    @PostMapping("")
    public void createFortune(){
        fortuneService.createFortune();
    }
}
