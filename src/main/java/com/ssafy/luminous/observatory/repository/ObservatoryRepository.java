package com.ssafy.luminous.observatory.repository;

import com.ssafy.luminous.observatory.domain.Observatory;
import com.ssafy.luminous.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface ObservatoryRepository extends JpaRepository<Observatory, Long> {
    @Override
    List<Observatory> findAll();

    List<Observatory> findByAddressStartsWith(String keyword);
}
