package com.ssafy.luminous.observatory.service;

import com.ssafy.luminous.observatory.domain.Observatory;
import com.ssafy.luminous.observatory.dto.ObservatoryReqDto;
import com.ssafy.luminous.observatory.repository.ObservatoryRepository;
import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservatoryService {

    private final ObservatoryRepository observatoryRepository;

    public List<Observatory> getObservatoryList(String keyword){
        List<Observatory> observatorys;

        observatorys= observatoryRepository.findByAddressStartsWith(keyword);


        return observatorys;
    }
}
