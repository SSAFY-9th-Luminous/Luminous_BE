package com.ssafy.luminous.fortune.controller;

import com.ssafy.luminous.fortune.service.FortuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/fortunes")
@RequiredArgsConstructor
public class FortuneController {

    private final FortuneService fortuneService;

    public String getFortune(){
        return "hi";
    }
}
