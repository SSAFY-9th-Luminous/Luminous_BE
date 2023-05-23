package com.ssafy.luminous.constellation.repository;

import com.ssafy.luminous.constellation.domain.Constellation12;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface Constellation12Repository extends JpaRepository<Constellation12,Long> {
    Constellation12 findByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date date1, Date date2);

}
