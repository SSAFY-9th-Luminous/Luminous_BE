package com.ssafy.luminous.place.repository;

import com.ssafy.luminous.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Override
    List<Place> findAll();
}
