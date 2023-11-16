package com.welcome.enjoytrip.tourboard.controller;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import com.welcome.enjoytrip.tourboard.model.service.TourboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tourboard")
public class TourBoardController {

    private final TourboardService tourboardService;

    public TourBoardController(TourboardService tourboardService) {
        this.tourboardService = tourboardService;
    }
    @PostMapping
    public ResponseEntity<?> tourboardWrite(@RequestBody TourBoardDto tourBoardDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardWrite(tourBoardDto);
            map.put("resmsg", "글쓰기 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "글쓰기 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> tourboardView(@PathVariable("id") int tourboard_id ) {
        Map<String, Object> map = new HashMap<>();
        try {
            TourBoardDto tourBoardDto = tourboardService.tourboardView(tourboard_id);
            map.put("resmsg", "패키지 불러오기 성공");
            map.put("tourboard", tourBoardDto);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "패키지 불러오기 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }
    @GetMapping
    public ResponseEntity<?> tourboardList() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<TourBoardDto> list = tourboardService.tourboardList();
            map.put("resmsg", "리스트 불러오기 성공");
            map.put("tourboards", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "리스트 불러오기 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @PatchMapping
    public ResponseEntity<?> tourboardPatch(@RequestBody TourBoardDto tourBoardDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardPatch(tourBoardDto);
            map.put("resmsg", "보드 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 수정 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @PutMapping
    public ResponseEntity<?> tourboardPut(@RequestBody TourBoardDto tourBoardDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardPut(tourBoardDto);
            map.put("resmsg", "보드 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 수정 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> tourboardDelete(@PathVariable("id") int tourboard_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            tourboardService.tourboardDelete(tourboard_id);
            map.put("resmsg", "보드 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 삭제 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }
}