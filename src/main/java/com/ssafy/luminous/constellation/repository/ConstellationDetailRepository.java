package com.ssafy.luminous.constellation.repository;

import com.ssafy.luminous.constellation.domain.ConstellationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstellationDetailRepository extends JpaRepository<ConstellationDetail,Long> {
}
