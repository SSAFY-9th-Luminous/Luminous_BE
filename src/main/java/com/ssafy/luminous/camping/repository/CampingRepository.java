package com.ssafy.luminous.camping.repository;

import com.ssafy.luminous.camping.domain.Camping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampingRepository extends JpaRepository<Camping, Long> {

}
