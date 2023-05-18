package com.ssafy.luminous.constellation.repository;

import com.ssafy.luminous.constellation.domain.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation,Long> {

}
