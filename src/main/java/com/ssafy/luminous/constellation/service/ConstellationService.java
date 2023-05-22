package com.ssafy.luminous.constellation.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.domain.ConstellationDetail;
import com.ssafy.luminous.constellation.dto.ConstellationDetailResDto;
import com.ssafy.luminous.constellation.dto.ConstellationReqDto;
import com.ssafy.luminous.constellation.repository.Constellation12Repository;
import com.ssafy.luminous.constellation.repository.ConstellationDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.ssafy.luminous.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@RequiredArgsConstructor
public class ConstellationService {
    private final ConstellationDetailRepository constellationDetailRepository;

    public List<ConstellationDetailResDto> getConstellationList() throws BaseException {
        try {
            List<ConstellationDetailResDto> constellationDetailResDtoList = new LinkedList<>();
            List<ConstellationDetail> constellationDetailList = constellationDetailRepository.findAll();
            for(ConstellationDetail detail : constellationDetailList){
                constellationDetailResDtoList.add(ConstellationDetailResDto
                                .builder()
                                .id(detail.getId())
                                .contentsName(detail.getContentsName())
                                .scientificName(detail.getScientificName())
                                .engName(detail.getEngName())
                                .meridianDateTime(detail.getMeridianDateTime())
                                .amountOfStars(detail.getAmountOfStars())
                                .size(detail.getSize())
                                .location(detail.getLocation())
                                .contentsDescription(detail.getContentsDescription())
                                .img(detail.getImg())
                                .build()
                        );
            }
            return constellationDetailResDtoList;
        }
        catch (Exception e){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
