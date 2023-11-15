package com.welcome.enjoytrip.board.model.service;

import com.welcome.enjoytrip.board.model.BoardDto;
import com.welcome.enjoytrip.board.model.BoardListDto;
import com.welcome.enjoytrip.board.model.CommentDto;

import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardListDto listBoard(Map<String, String> map) throws Exception;
    void writeBoard(BoardDto boardDto) throws Exception;

    BoardDto getBoard(int boardId) throws Exception;

    void updateViews(int boardId) throws Exception;

    void modifyBoard(BoardDto boardDto) throws Exception;

    void deleteBoard(int articleNo) throws Exception;

    void writeComment(CommentDto commentDto) throws Exception;

    List<CommentDto> getComment(int boardId) throws Exception;
}
