package com.ssafy.luminous.constellation.repository;

import com.ssafy.luminous.constellation.domain.Constellation12;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstellationRepository extends JpaRepository<Constellation12,Long> {

}
