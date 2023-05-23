package com.ssafy.luminous.camping.dto;

import com.ssafy.luminous.camping.domain.Camping;
import com.ssafy.luminous.camping.domain.CampingImage;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CampingDetailResponseDto {

    private Camping camping;

    private List<CampingImage> campingImageList;
}
