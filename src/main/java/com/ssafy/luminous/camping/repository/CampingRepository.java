package com.ssafy.luminous.camping.repository;

import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.dto.CampingListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {
    List<Camping> findByDoNameContaining(String region);
}
