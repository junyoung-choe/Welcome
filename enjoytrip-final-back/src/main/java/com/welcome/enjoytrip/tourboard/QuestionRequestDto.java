package com.welcome.enjoytrip.tourboard;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class QuestionRequestDto implements Serializable {
    private String question;
}