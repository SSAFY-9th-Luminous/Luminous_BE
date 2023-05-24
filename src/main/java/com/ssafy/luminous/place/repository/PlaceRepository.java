package com.ssafy.luminous.place.repository;

import com.ssafy.luminous.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Place> findByPlaceNameContainsOrderByIdDesc(String keyword);

    List<Place> findByPlaceDescriptionContainsOrderByIdDesc(String keyword);

    List<Place> findByMember_MemberNameContains(String keyword);



    Optional<Place> findByIdAndMember_id(Long id, Long memberId);




    @Modifying
    @Query(value = "update place set place.hit = place.hit + 1 where place.id = :id", nativeQuery = true)
    int updateHit(@Param("id") Long id);
}
