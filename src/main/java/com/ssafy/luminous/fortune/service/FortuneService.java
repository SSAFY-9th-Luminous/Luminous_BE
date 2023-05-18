package com.ssafy.luminous.fortune.service;

import com.ssafy.luminous.constellation.domain.Constellation;
import com.ssafy.luminous.constellation.service.ConstellationService;
import com.ssafy.luminous.fortune.domain.Fortune;
import com.ssafy.luminous.fortune.repository.FortuneRepository;
import com.ssafy.luminous.util.gpt.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.MetalIconFactory;
import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FortuneService {

    private final FortuneRepository fortuneRepository;

    private final ConstellationService constellationService;
    private final ChatGPTService chatGPTService;


    public Fortune getFortune(Long id) {
        return fortuneRepository.findById(id).orElseThrow();

    }

    public void createFortune() {

        List<Constellation> constellationList = constellationService.getConstellationList();
        for (Constellation constellation : constellationList) {
            String name = constellation.getConstellationDetail().getContentsName();
//            String prompt = name + "의 오늘의 운세는 어떤지 긍정적인 것과 부정적인 것에 대해서 작성해주고, 오늘의 추천 아이템 정보도 알려줘";
            String prompt = name+"의 오늘의 운세 20자 이하로 간단하게 알려줘";
            // maxTokens 응답 컨텍스트의 길이
            // tempeature 창의성
            String answer = chatGPTService.generateText(prompt, 1, 150);

            Fortune fortune = Fortune.builder()
                    .date(new Date(System.currentTimeMillis()))
                    .description(answer)
                    .constellation(constellation)
                    .build()
                    ;

            fortuneRepository.save(fortune);
        }


    }
}
