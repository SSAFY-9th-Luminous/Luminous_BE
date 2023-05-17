package com.ssafy.luminous.place.service;

import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.dto.PlaceUpdateReqDto;
import com.ssafy.luminous.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<PlaceListResDto> getPlaceList(String category, String keyword){
        List<Place> places;

        List<PlaceListResDto> newPlaces =  new ArrayList<>();

        // 유저이름
        if("user".equals(category)){
            places = placeRepository.findByMember_MemberNameContains(keyword);
        }
        // 설명
        else if ("desc".equals(category)) {
            places= placeRepository.findByPlaceDescriptionContains(keyword);
        }
        // 장소이름
        else {
            places = placeRepository.findByPlaceNameContains(keyword);
        }

        for(Place place : places){
            newPlaces.add(PlaceListResDto.builder()
                    .id(place.getId())
                    .placeName(place.getPlaceName())
                    .placeDescription(place.getPlaceDescription())
                    .hit(place.getHit())
                    .createdDate(place.getCreatedDate())
                    .rate(place.getRate())
                    .member(place.getMember())
                    .build());
        }

        return newPlaces;
    }

    public Place postPlace(PlacePostReqDto placePostReqDto, Long memberId){
           return placeRepository.save(placePostReqDto.toEntity(memberId));
    }
    public Place postPlace(PlacePostReqDto placePostReqDto){
        return placeRepository.save(placePostReqDto.toEntity(1L));
    }
    public void deletePlace(Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if(place.isEmpty()){
            System.out.println("사용자의 게시글이 아니에요~");
            throw new RuntimeException("사용자 게시글이 아녜요");
        }
        placeRepository.deleteById(id);
    }
    public void deletePlace(Long id, Long memberId) {
        Optional<Place> place = placeRepository.findByIdAndMember_id(id,memberId);
        if(place.isEmpty()){
            System.out.println("사용자의 게시글이 아니에요~");
            throw new RuntimeException("사용자 게시글이 아녜요");
        }
        placeRepository.deleteById(id);
    }
    public Place updatePlace(Long id, PlaceUpdateReqDto placeUpdateReqDto){
        Optional<Place> place = placeRepository.findById(id);
        if(place.isEmpty()){
            System.out.println("사용자의 게시글이 아니에요~");
            throw new RuntimeException("사용자 게시글이 아녜요");
        }
        place.get().update(placeUpdateReqDto);
        return place.get();

    }
    public Place updatePlace(Long id, PlaceUpdateReqDto placeUpdateReqDto, Long memberId){
        Optional<Place> place = placeRepository.findByIdAndMember_id(id,memberId);
        if(place.isEmpty()){
            System.out.println("사용자의 게시글이 아니에요~");
            throw new RuntimeException("사용자 게시글이 아녜요");
        }
        place.get().update(placeUpdateReqDto);
        return place.get();

    }

    public Place getPlace(Long id) {
        Place place =placeRepository.findById(id).orElseThrow();
        return place;
    }
}
