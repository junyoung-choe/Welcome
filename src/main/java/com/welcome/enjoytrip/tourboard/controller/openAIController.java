package com.welcome.enjoytrip.tourboard.controller;

import com.welcome.enjoytrip.tourboard.ChatGptResponseDto;
import com.welcome.enjoytrip.tourboard.QuestionRequestDto;
import com.welcome.enjoytrip.tourboard.model.service.ChatGptService;
import com.welcome.enjoytrip.tourboard.model.service.TourboardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat-gpt")
public class openAIController {

    private final ChatGptService chatGptService;
    private final TourboardService tourboardService;

    public openAIController(ChatGptService chatGptService, TourboardService tourboardService) {
        this.chatGptService = chatGptService;
        this.tourboardService = tourboardService;
    }

    @PostMapping("/question")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) throws Exception{
        List<String> list = tourboardService.getDestinationForGPT();
        String c = "";
        for (int i = 0; i < list.size(); i++) {
            c += list.get(i);
        }
        String que = c + " 여기에 있는 나라 및 지역 중에서 (" + requestDto.getQuestion() + ") 괄호안에 있는 문장과 가장 연관 있는 곳 하나만 명사로 답해줘" ;
        requestDto.setQuestion(que);
        System.out.println(requestDto);
        ChatGptResponseDto dto = chatGptService.askQuestion(requestDto);
        return dto;
    }
}
