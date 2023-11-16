package com.welcome.enjoytrip.location.controller;

import com.welcome.enjoytrip.location.model.LocationDto;
import com.welcome.enjoytrip.location.model.service.LocationService;
import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping
    public ResponseEntity<?> locationWrite(@RequestBody LocationDto locationDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            locationService.locationWrite(locationDto);
            map.put("resmsg", "location 등록 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "location 등록 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @GetMapping
    public ResponseEntity<?> locationList() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<LocationDto> list = locationService.locationList();
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
    public ResponseEntity<?> locationPut(@RequestBody LocationDto locationDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            locationService.locationPut(locationDto);
            map.put("resmsg", "보드 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 수정 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> locationDelete(@PathVariable("id") int location_id) {
        Map<String, Object> map = new HashMap<>();
        try {
            locationService.locationDelete(location_id);
            map.put("resmsg", "보드 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "보드 삭제 실패");
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

}
