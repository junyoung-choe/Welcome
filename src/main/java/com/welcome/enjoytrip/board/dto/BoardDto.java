package com.welcome.enjoytrip.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private long board_id;
    private long user_id;
    private String board_title;
    private String board_content;
    private int board_views;
    private LocalDateTime board_regDate;
}
