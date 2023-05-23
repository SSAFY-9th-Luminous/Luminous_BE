package com.ssafy.luminous.constellation.repository;

import com.ssafy.luminous.constellation.domain.Constellation12;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface Constellation12Repository extends JpaRepository<Constellation12,Long> {
    Optional<Constellation12> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date date1, Date date2);

}
