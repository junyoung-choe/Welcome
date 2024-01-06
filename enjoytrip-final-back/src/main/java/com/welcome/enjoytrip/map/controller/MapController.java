package com.welcome.enjoytrip.map.controller;

import com.welcome.enjoytrip.map.model.CodeDto;
import com.welcome.enjoytrip.map.model.service.MapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    private MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/sido")
    public ResponseEntity<List<CodeDto>> sido() throws Exception {
        return new ResponseEntity<List<CodeDto>>(mapService.getSido(), HttpStatus.OK);
    }

    @GetMapping("/gugun")
    public ResponseEntity<List<CodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
        return new ResponseEntity<List<CodeDto>>(mapService.getGugunInSido(sido), HttpStatus.OK);
    }
}
