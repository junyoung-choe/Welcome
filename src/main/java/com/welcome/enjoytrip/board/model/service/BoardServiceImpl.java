package com.welcome.enjoytrip.board.model.service;

import com.welcome.enjoytrip.board.model.BoardDto;
import com.welcome.enjoytrip.board.model.BoardListDto;
import com.welcome.enjoytrip.board.model.FileInfoDto;
import com.welcome.enjoytrip.board.model.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public BoardListDto listBoard(Map<String, String> map) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "b.user_id");
        List<BoardDto> list = boardMapper.listArticle(param);

        if ("user_id".equals(key))
            param.put("key", key == null ? "" : "user_id");
        int totalArticleCount = boardMapper.getTotalArticleCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        BoardListDto boardListDto = new BoardListDto();
        boardListDto.setBoards(list);
        boardListDto.setCurrentPage(currentPage);
        boardListDto.setTotalPageCount(totalPageCount);

        return boardListDto;
    }

    @Override
    @Transactional
    public void writeBoard(BoardDto boardDto) throws Exception {
        boardMapper.writeArticle(boardDto);
        List<FileInfoDto> fileInfos = boardDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            boardMapper.registerFile(boardDto);
        }
    }

    @Override
    public BoardDto getBoard(int boardId) throws Exception {
        return boardMapper.getBoard(boardId);
    }

    @Override
    public void updateViews(int boardId) throws Exception {
        boardMapper.updateViews(boardId);
    }

    @Override
    public void modifyBoard(BoardDto boardDto) throws Exception {
        boardMapper.modifyBoard(boardDto);
    }

    @Override
    public void deleteBoard(int boardId) throws Exception {
        boardMapper.deleteBoard(boardId);
    }
}
