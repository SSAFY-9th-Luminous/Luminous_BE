package com.ssafy.luminous.place.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.place.domain.Place;
import com.ssafy.luminous.place.dto.PlacePostReqDto;
import com.ssafy.luminous.place.dto.PlaceResDto;
import com.ssafy.luminous.place.dto.PlaceUpdateReqDto;
import com.ssafy.luminous.place.repository.PlaceRepository;
import com.ssafy.luminous.util.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.luminous.config.BaseResponseStatus.DATABASE_ERROR;
import static com.ssafy.luminous.config.BaseResponseStatus.NOT_OWNER;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final S3Service s3Service;

    @Transactional(readOnly = true)
    public List<PlaceResDto> getPlaceList(String category, String keyword) throws BaseException {
        List<Place> places;

        List<PlaceResDto> newPlaces = new ArrayList<>();

        try {
            // 유저이름
            if ("user".equals(category)) {
                places = placeRepository.findByMember_MemberNameContains(keyword);
            }
            // 설명
            else if ("desc".equals(category)) {
                places = placeRepository.findByPlaceDescriptionContainsOrderByIdDesc(keyword);
            }
            // 장소이름
            else {
                places = placeRepository.findByPlaceNameContainsOrderByIdDesc(keyword);
            }

            for (Place place : places) {
                newPlaces.add(PlaceResDto.builder()
                        .id(place.getId())
                        .placeName(place.getPlaceName())
                        .placeDescription(place.getPlaceDescription())
                        .latitude(place.getLatitude())
                        .longitude(place.getLongitude())
                        .address(place.getAddress())
                        .hit(place.getHit())
                        .createdDate(place.getCreatedDate())
                        .lastModifiedDate(place.getLastModifiedDate())
                        .visitedDate(place.getVisitedDate())
                        .rate(place.getRate())
                        .img(place.getImg())
                        .member(place.getMember())
                        .build());
            }
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }

        return newPlaces;
    }

    @Transactional
    public void postPlace(PlacePostReqDto placePostReqDto, Long memberId) throws BaseException {
        try {
            if(!placePostReqDto.getImg().isEmpty()){
                String url = s3Service.uploadImage(placePostReqDto.getImg());
                placePostReqDto.setUrl(url);
            }
            placeRepository.save(placePostReqDto.toEntity(memberId));
        }
        catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
        catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public void deletePlace(Long id, Long memberId) throws BaseException {
        Optional<Place> place = placeRepository.findByIdAndMember_id(id, memberId);
        try {

            if (place.isEmpty()) {
                System.out.println("사용자의 게시글이 아니에요~");
                throw new BaseException(NOT_OWNER);
            }

            if (place.get().getImg() != null) {
                s3Service.deleteImage(place.get().getImg());
            }
            placeRepository.deleteById(id);
        }
        catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
        catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    @Transactional
    public void updatePlace(Long id, PlaceUpdateReqDto placeUpdateReqDto, Long memberId) throws BaseException {

        try {
            Optional<Place> place = placeRepository.findByIdAndMember_id(id, memberId);
            if (place.isEmpty()) {
                System.out.println("사용자의 게시글이 아니에요~");
                throw new BaseException(NOT_OWNER);
            }
            if(!placeUpdateReqDto.getImg().isEmpty()){
                s3Service.deleteImage(place.get().getImg());
                String url = s3Service.uploadImage(placeUpdateReqDto.getImg());
                placeUpdateReqDto.setUrl(url);
            }
            else{
                placeUpdateReqDto.setUrl(place.get().getImg());
            }
            place.get().update(placeUpdateReqDto);

        }
        catch (BaseException e){
            throw new BaseException(e.getStatus());
        }
        catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }


    }
    @Transactional(readOnly = true)
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
