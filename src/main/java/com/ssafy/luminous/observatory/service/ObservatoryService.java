package com.ssafy.luminous.observatory.service;

import com.ssafy.luminous.observatory.domain.Observatory;
import com.ssafy.luminous.observatory.dto.ObservatoryListResponseDto;
import com.ssafy.luminous.observatory.repository.ObservatoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservatoryService {

    private final ObservatoryRepository observatoryRepository;

    public List<Observatory> getObservatoryListToMap(String keyword){
        List<Observatory> observatories;

        observatories= observatoryRepository.findByAddressStartsWith(keyword);

        return observatories;
    }

    public List<ObservatoryListResponseDto> getObservatoryList(String keyword) {
        List<Observatory> observatories = observatoryRepository.findByAddressStartsWith(keyword);
        List<ObservatoryListResponseDto> observatoryListResponseDtos = new ArrayList<>();
        for (Observatory obs : observatories) {
            ObservatoryListResponseDto newObs = ObservatoryListResponseDto.builder()
                    .id(obs.getId())
                    .siName(obs.getAddress().substring(0, 2))
                    .observatoryName(obs.getObservatoryName())
                    .address(obs.getAddress())
                    .build();

            observatoryListResponseDtos.add(newObs);
        }

        return observatoryListResponseDtos;
    }
}
