package com.welcome.enjoytrip.reservation.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {
    private long reservation_id;
    private long user_id;
    private long tourboard_id;
    private LocalDateTime reservation_regDate;
    private boolean reservation_yn;
    private int reservation_cnt;
}
