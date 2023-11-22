package com.welcome.enjoytrip.tourboard.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TourBoardDto {
    private long tourboard_id;
    private long user_id;
    private String tourboard_tourName;
    private String tourboard_keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tourboard_regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tourboard_startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tourboard_endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tourboard_deadLineDate;
    private int tourboard_price;
    private int tourboard_salePrice;
    private int tourboard_stock;
    private int tourboard_stockCnt;
    private boolean tourboard_fnishedYn;
    private String tourboard_departure;
    private String tourboard_destination;
    private int tourboard_views;
    private List<FileInfoDto> fileInfos;
}
