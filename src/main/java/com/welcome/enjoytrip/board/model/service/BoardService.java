package com.welcome.enjoytrip.board.model.service;

import com.welcome.enjoytrip.board.model.BoardDto;
import com.welcome.enjoytrip.board.model.BoardListDto;

import java.util.Map;

public interface BoardService {

    BoardListDto listBoard(Map<String, String> map) throws Exception;
    void writeBoard(BoardDto boardDto) throws Exception;

    BoardDto getBoard(int boardId) throws Exception;

    void updateViews(int boardId) throws Exception;

    void modifyBoard(BoardDto boardDto) throws Exception;

    void deleteBoard(int articleNo) throws Exception;
}
