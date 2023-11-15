package com.welcome.enjoytrip.board.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto {
    private long board_id;
    private long user_id;
    private String board_title;
    private String board_content;
    private int board_views;
    private LocalDateTime board_regDate;
    private List<FileInfoDto> fileInfos;

}
