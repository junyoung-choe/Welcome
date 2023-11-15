package com.welcome.enjoytrip.board.model.mapper;

import com.welcome.enjoytrip.board.model.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;

    void writeArticle(BoardDto boardDto) throws SQLException;

    void registerFile(BoardDto boardDto) throws Exception;

    BoardDto getBoard(int boardId) throws SQLException;

    void updateViews(int boardId) throws SQLException;

    void modifyBoard(BoardDto boardDto) throws SQLException;

    void deleteBoard(int boardId) throws SQLException;

}
