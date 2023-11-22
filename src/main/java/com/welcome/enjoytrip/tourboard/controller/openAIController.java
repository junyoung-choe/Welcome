package com.welcome.enjoytrip.tourboard.controller;

import com.welcome.enjoytrip.tourboard.ChatGptResponseDto;
import com.welcome.enjoytrip.tourboard.QuestionRequestDto;
import com.welcome.enjoytrip.tourboard.model.service.ChatGptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-gpt")
public class openAIController {

    private final ChatGptService chatGptService;

    public openAIController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/question")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        String que = "(" + requestDto.getQuestion() + ") 괄호 안에 적는 문장에 가장 가까운 나라를 나라 이름만 명사로 답해줘 대한민국, 한국은 빼고";
        requestDto.setQuestion(que);
        System.out.println(requestDto);
        ChatGptResponseDto dto = chatGptService.askQuestion(requestDto);
        return dto;
    }
}
