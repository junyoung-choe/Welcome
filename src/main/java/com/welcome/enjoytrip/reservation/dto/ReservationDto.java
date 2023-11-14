package com.welcome.enjoytrip.reservation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private long reservation_id;
    private long user_id;
    private long tourboard_id;
    private LocalDateTime reservation_regDate;
    private boolean reservation_yn;
}
