package com.ssafy.luminous.constellation.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.repository.Constellation12Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.luminous.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class Constellation12Service {

    private final Constellation12Repository constellation12Repository;

    public List<Constellation12> getConstellation12List() throws BaseException {
        try {
            return constellation12Repository.findAll();
        }
        catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
