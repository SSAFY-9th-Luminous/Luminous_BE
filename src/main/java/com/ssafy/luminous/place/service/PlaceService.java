package com.ssafy.luminous.place.service;

import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<PlaceListResDto> getPlaceList(){
        List<Place> places = placeRepository.findAll();
        System.out.println(places);
        List<PlaceListResDto> newPlaces =  new ArrayList<>();

        for(Place place : places){
            newPlaces.add(PlaceListResDto.builder()
                    .id(place.getId())
                    .placeName(place.getPlaceName())
                    .placeDescription(place.getPlaceDescription())
                    .hit(place.getHit())
                    .createdDate(place.getCreatedDate())
                    .rate(place.getRate())
                    .memberId(place.getMember().getId())
                    .build());
        }

        return newPlaces;
    }
}
