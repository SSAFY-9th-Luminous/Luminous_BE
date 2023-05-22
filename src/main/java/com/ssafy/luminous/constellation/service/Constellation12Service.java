package com.ssafy.luminous.constellation.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.dto.Constellation12ResDto;
import com.ssafy.luminous.constellation.repository.Constellation12Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.ssafy.luminous.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class Constellation12Service {

    private final Constellation12Repository constellation12Repository;

    public List<Constellation12ResDto> getConstellation12List() throws BaseException {
        try {
            List<Constellation12ResDto> constellation12ResDtoList = new LinkedList<>();

            List<Constellation12> constellation12List = constellation12Repository.findAll();
            for(Constellation12 constellation12 : constellation12List){
                constellation12ResDtoList.add(Constellation12ResDto
                        .builder()
                                .id(constellation12.getId())
                                .startDate(constellation12.getStartDate())
                                .endDate(constellation12.getEndDate())
                                .contentsName(constellation12.getConstellationDetail().getContentsName())
                                .scientificName(constellation12.getConstellationDetail().getScientificName())
                                .engName(constellation12.getConstellationDetail().getEngName())
                                .meridianDateTime(constellation12.getConstellationDetail().getMeridianDateTime())
                                .amountOfStars(constellation12.getConstellationDetail().getAmountOfStars())
                                .size(constellation12.getConstellationDetail().getSize())
                                .location(constellation12.getConstellationDetail().getLocation())
                                .contentsDescription(constellation12.getConstellationDetail().getContentsDescription())
                                .img(constellation12.getConstellationDetail().getImg())
                        .build());
            }
            return constellation12ResDtoList;
        }
        catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
