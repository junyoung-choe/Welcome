package com.welcome.enjoytrip.location.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationDto {
    private long location_id;
    private long tourboard_id;
    private String location_name;
    private LocalDateTime location_startDate;
    private LocalDateTime location_endDate;
}

