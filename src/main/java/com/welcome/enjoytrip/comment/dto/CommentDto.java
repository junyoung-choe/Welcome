package com.welcome.enjoytrip.comment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private long comment_id;
    private long user_id;
    private long board_id;
    private String comment_content;
    private LocalDateTime comment_regDate;
}
