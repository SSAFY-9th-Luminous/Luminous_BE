package com.ssafy.luminous.place.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.config.BaseResponseStatus;
import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlaceListResDto;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.dto.PlaceUpdateReqDto;
import com.ssafy.luminous.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.luminous.config.BaseResponseStatus.DATABASE_ERROR;
import static com.ssafy.luminous.config.BaseResponseStatus.NOT_OWNER;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<PlaceListResDto> getPlaceList(String category, String keyword) throws BaseException {
        List<Place> places;

        List<PlaceListResDto> newPlaces = new ArrayList<>();

        try {
            // 유저이름
            if ("user".equals(category)) {
                places = placeRepository.findByMember_MemberNameContains(keyword);
            }
            // 설명
            else if ("desc".equals(category)) {
                places = placeRepository.findByPlaceDescriptionContains(keyword);
            }
            // 장소이름
            else {
                places = placeRepository.findByPlaceNameContains(keyword);
            }

            for (Place place : places) {
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
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        return newPlaces;
    }

    public Place postPlace(PlacePostReqDto placePostReqDto, Long memberId) throws BaseException {
        try {
            return placeRepository.save(placePostReqDto.toEntity(memberId));
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public Place postPlace(PlacePostReqDto placePostReqDto) throws BaseException {
        try {
            return placeRepository.save(placePostReqDto.toEntity(1L));
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deletePlace(Long id) throws BaseException {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isEmpty()) {
            System.out.println("사용자의 게시글이 아니에요~");
            throw new BaseException(NOT_OWNER);
        }
        placeRepository.deleteById(id);
    }

    public void deletePlace(Long id, Long memberId) throws BaseException {
        Optional<Place> place = placeRepository.findByIdAndMember_id(id, memberId);
        if (place.isEmpty()) {
            System.out.println("사용자의 게시글이 아니에요~");
            throw new BaseException(NOT_OWNER);
        }
        placeRepository.deleteById(id);
    }

    public Place updatePlace(Long id, PlaceUpdateReqDto placeUpdateReqDto) throws BaseException {
        Optional<Place> place = placeRepository.findById(id);

        if (place.isEmpty()) {
            System.out.println("사용자의 게시글이 아니에요~");
            throw new BaseException(NOT_OWNER);
        }
        return placeRepository.save(place.get().update(placeUpdateReqDto));

    }

    public Place updatePlace(Long id, PlaceUpdateReqDto placeUpdateReqDto, Long memberId) throws BaseException {

        Optional<Place> place = placeRepository.findByIdAndMember_id(id, memberId);
        if (place.isEmpty()) {
            System.out.println("사용자의 게시글이 아니에요~");
            throw new BaseException(NOT_OWNER);
        }
        place.get().update(placeUpdateReqDto);
        return place.get();


    }

    public Place getPlace(Long id) throws BaseException {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isEmpty()) {
            throw new BaseException(NOT_OWNER);
        }
        return place.get();
    }

    @Transactional
    public int updateHit(Long id) {
        return placeRepository.updateHit(id);
    }
}
