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
        String que = "(" + requestDto.getQuestion() + ") 괄호안에 있는 문장에 해당하는 나라 말해줘, 하나의 나라만 명사로 말해줘, 무조건 나라이름으로 답해줘";
        requestDto.setQuestion(que);
        System.out.println(requestDto);
        ChatGptResponseDto dto = chatGptService.askQuestion(requestDto);
        return dto;
    }
}
