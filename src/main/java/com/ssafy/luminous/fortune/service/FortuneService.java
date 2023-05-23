package com.ssafy.luminous.fortune.service;

import com.ssafy.luminous.config.BaseException;
import com.ssafy.luminous.constellation.domain.Constellation12;
import com.ssafy.luminous.constellation.repository.Constellation12Repository;
import com.ssafy.luminous.constellation.service.Constellation12Service;
import com.ssafy.luminous.fortune.domain.Fortune;
import com.ssafy.luminous.fortune.dto.FortuneResDto;
import com.ssafy.luminous.fortune.dto.TodayFortuneResDto;
import com.ssafy.luminous.fortune.repository.FortuneRepository;
import com.ssafy.luminous.member.domain.Member;
import com.ssafy.luminous.member.repository.MemberRepository;
import com.ssafy.luminous.member.service.MemberService;
import com.ssafy.luminous.util.gpt.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.luminous.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class FortuneService {

    private final FortuneRepository fortuneRepository;
    private final Constellation12Repository constellation12Repository;
    private final Constellation12Service constellation12Service;
    private final MemberRepository memberRepository;
    private final ChatGPTService chatGPTService;


    @Transactional
    public Fortune getFortune(Long id) {
        return fortuneRepository.findById(id).orElseThrow();

    }

    @Transactional(readOnly = true)
    public List<FortuneResDto> getTodayFortuneList() throws BaseException {
        try {
            List<Fortune> fortuneList = fortuneRepository.findByDate(new Date(System.currentTimeMillis()));
            List<FortuneResDto> fortuneResDtoList = new LinkedList<>();
            for (Fortune fortune : fortuneList) {
                fortuneResDtoList.add(FortuneResDto.builder()
                        .contentsId(fortune.getConstellation12().getId())
                        .contentsName(fortune.getConstellation12().getConstellationDetail().getContentsName())
                        .description(fortune.getDescription())
                        .img(fortune.getConstellation12().getImg())
                        .build())
                ;

            }
            return fortuneResDtoList;
        } catch (Exception e) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void createFortune() throws BaseException {
        try {
            // 이미 오늘 만든 운세가 있다면
            if (fortuneRepository.findByDate(new Date(System.currentTimeMillis())).size() > 12) {
                return;
            }

            fortuneRepository.deleteAll();
            List<Constellation12> constellation12List = constellation12Repository.findAll();
            for (Constellation12 constellation12 : constellation12List) {
                String name = constellation12.getConstellationDetail().getContentsName();
//            String prompt = name + "의 오늘의 운세는 어떤지 긍정적인 것과 부정적인 것에 대해서 작성해주고, 오늘의 추천 아이템 정보도 알려줘";
                String prompt = name + "의 오늘의 운세 20자 이하로 간단하게 알려줘";
                // maxTokens 응답 컨텍스트의 길이
                // tempeature 창의성
                String answer = chatGPTService.generateText(prompt, 1, 150);

                Fortune fortune = Fortune.builder()
                        .date(new Date(System.currentTimeMillis()))
                        .description(answer)
                        .constellation12(constellation12)
                        .build();

                fortuneRepository.save(fortune);
            }
        } catch (Exception e) {
            throw new BaseException(GPT_API_ERROR);
        }


    }

    @Transactional(readOnly = true)
    public TodayFortuneResDto getTodayFortune(Long memberId) throws BaseException {
        try {
            Optional<Member> member = Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(() -> new BaseException(NOT_MATCHED_ID)));
            Fortune fortune = fortuneRepository.findByConstellation12_id(member.get().getConstellation12().getId());
            TodayFortuneResDto todayFortuneResDto = TodayFortuneResDto.builder()
                    .name(fortune.getConstellation12().getConstellationDetail().getContentsName())
                    .description(fortune.getDescription())
                    .build();
            return todayFortuneResDto;
        } catch (Exception e) {
            throw new BaseException(CAN_NOT_CALL_TODAY_FORTUNE);
        }

    }
}
