package com.ssafy.luminous.observatory.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.observatory.domain.Observatory;
import com.ssafy.luminous.observatory.dto.ObservatoryListResponseDto;
import com.ssafy.luminous.observatory.repository.ObservatoryRepository;
import com.ssafy.luminous.place.domain.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.luminous.config.BaseResponseStatus.NOT_OWNER;

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
                    .siDoName(obs.getAddress().split(" ")[0])
                    .observatoryName(obs.getObservatoryName())
                    .address(obs.getAddress())
                    .build();

            observatoryListResponseDtos.add(newObs);
        }

        return observatoryListResponseDtos;
    }

    @Transactional(readOnly = true)
    public Observatory getObservatory(Long id) throws BaseException {
        Optional<Observatory> observatory = observatoryRepository.findById(id);
        if (observatory.isEmpty()) {
            throw new BaseException(NOT_OWNER);
        }
        return observatory.get();
    }

    public List<ObservatoryListResponseDto> getObservatoryListByLocation() {
        List<Observatory> observatoryList = observatoryRepository.findByAddressStartsWith("서울");
        List<ObservatoryListResponseDto> observatoryListResponseDtoList = new ArrayList<>();
        for (Observatory obs : observatoryList) {
            if (obs.getImageUrl() == null) continue;

            observatoryListResponseDtoList.add(ObservatoryListResponseDto.builder()
                    .id(obs.getId())
                    .address(obs.getAddress())
                    .observatoryName(obs.getObservatoryName())
                    .imageUrl(obs.getImageUrl())
                    .build());
        }

        return observatoryListResponseDtoList;
    }
}
