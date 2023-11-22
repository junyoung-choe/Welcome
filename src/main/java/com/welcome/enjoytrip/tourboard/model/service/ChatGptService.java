package com.welcome.enjoytrip.tourboard.model.service;

import com.welcome.enjoytrip.tourboard.ChatGptRequestDto;
import com.welcome.enjoytrip.tourboard.ChatGptResponseDto;
import com.welcome.enjoytrip.tourboard.QuestionRequestDto;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class ChatGptService {
    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(GptConfig.MEDIA_TYPE));
        headers.add(GptConfig.AUTHORIZATION, GptConfig.BEARER + GptConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                GptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        System.out.println(responseEntity.getBody().getChoices());

        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                GptConfig.MODEL,
                                requestDto.getQuestion(),
                                GptConfig.MAX_TOKEN,
                                GptConfig.TEMPERATURE,
                                GptConfig.TOP_P
                        )
                )
        );
    }
}
