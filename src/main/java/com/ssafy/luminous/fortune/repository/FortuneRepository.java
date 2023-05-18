package com.ssafy.luminous.fortune.repository;

import com.ssafy.luminous.fortune.domain.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FortuneRepository extends JpaRepository<Fortune,Long> {
    Optional<Fortune> findById(Long id);
    List<Fortune> findByDate(Date date);

}
