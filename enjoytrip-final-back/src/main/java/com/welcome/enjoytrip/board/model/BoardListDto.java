package com.welcome.enjoytrip.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDto {

    private List<BoardDto> boards;
    private int currentPage;
    private int totalPageCount;

}
