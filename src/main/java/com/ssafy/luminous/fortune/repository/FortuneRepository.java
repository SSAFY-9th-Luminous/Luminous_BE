package com.ssafy.luminous.fortune.repository;

import com.ssafy.luminous.fortune.domain.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FortuneRepository extends JpaRepository<Fortune,Long> {
}
