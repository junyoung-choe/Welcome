package com.welcome.enjoytrip.board.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    private long comment_id;
    private long user_id;
    private long board_id;
    private String comment_content;
    private LocalDateTime comment_regDate;
}
