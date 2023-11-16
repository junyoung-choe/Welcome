package com.welcome.enjoytrip.reservation.controller;

import com.welcome.enjoytrip.location.model.LocationDto;
import com.welcome.enjoytrip.reservation.model.ReservationDto;
import com.welcome.enjoytrip.reservation.model.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> reservationWrite(@RequestBody ReservationDto reservationDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            reservationService.reservationWrite(reservationDto);
            map.put("resmsg", "reservation 등록 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "reservation 등록 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @GetMapping
    public ResponseEntity<?> reservationList() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ReservationDto> list = reservationService.reservationList();
            map.put("resmsg", "리스트 불러오기 성공");
            map.put("tourboards", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "리스트 불러오기 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @PutMapping
    public ResponseEntity<?> reservationPut(@RequestBody ReservationDto reservationDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            reservationService.reservationPut(reservationDto);
            map.put("resmsg", "보드 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 수정 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> reservationDelete(@PathVariable("id") int reservation_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            reservationService.reservationDelete(reservation_id);
            map.put("resmsg", "예약 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "예약 삭제 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }
}
