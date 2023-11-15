package com.welcome.enjoytrip.board.controller;

import com.welcome.enjoytrip.board.model.BoardDto;
import com.welcome.enjoytrip.board.model.BoardListDto;
import com.welcome.enjoytrip.board.model.service.BoardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<?> boardList(@RequestParam Map<String, String> map) {
        try {
            BoardListDto boardListDto = boardService.listBoard(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(boardListDto);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> boardWrite(@RequestBody BoardDto boardDto) {
        try {
            boardService.writeBoard(boardDto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> boardView( @PathVariable("boardId") int boardId ) throws Exception {
        boardService.updateViews(boardId);
        return new ResponseEntity<BoardDto>(boardService.getBoard(boardId), HttpStatus.OK);
    }

    @GetMapping("/modify/{boardId}")
    public ResponseEntity<BoardDto> getModifyBoard( @PathVariable("boardId") int boardId ) throws Exception {
        return new ResponseEntity<BoardDto>(boardService.getBoard(boardId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> boardModify( @RequestBody BoardDto boardDto) throws Exception {
        boardService.modifyBoard(boardDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> boardDelete(@PathVariable("boardId") int boardId) throws Exception {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().build();

    }



    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
