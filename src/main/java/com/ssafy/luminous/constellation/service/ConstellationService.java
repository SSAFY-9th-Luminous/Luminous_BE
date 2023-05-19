package com.ssafy.luminous.constellation.service;

import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.repository.ConstellationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstellationService {

    private final ConstellationRepository constellationRepository;

    public List<Constellation12> getConstellationList(){
        return constellationRepository.findAll();
    }

}
