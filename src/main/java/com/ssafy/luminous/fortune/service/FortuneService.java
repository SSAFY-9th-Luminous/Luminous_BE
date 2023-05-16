package com.ssafy.luminous.fortune.service;

import com.ssafy.luminous.fortune.domain.Fortune;
import com.ssafy.luminous.fortune.repository.FortuneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FortuneService {

    private final FortuneRepository fortuneRepository;

    public Fortune getFortune(Long id){
        return fortuneRepository.findById(id).orElseThrow();

    }
}
