package com.ssafy.luminous.place.repository;

import com.ssafy.luminous.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Override
    List<Place> findAll();

    List<Place> findByOrderByCreatedDateDesc();

    List<Place> findByOrderByRate();

    List<Place> findByOrderByHit();

    List<Place> findByPlaceNameContains(String keyword);

    List<Place> findByPlaceDescriptionContains(String keyword);

    List<Place> findByMember_MemberNameContains(String keyword);

    Optional<Place> findByIdAndMember_id(Long id, Long memberId);
}
