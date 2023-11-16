package com.welcome.enjoytrip.tourboard.model;

import lombok.*;

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
    private LocalDateTime tourboard_regDate;
    private LocalDateTime tourboard_startDate;
    private LocalDateTime tourboard_endDate;
    private LocalDateTime tourboard_deadLineDate;
    private int tourboard_price;
    private int tourboard_salePrice;
    private int tourboard_stock;
    private int tourboard_stockCnt;
    private boolean tourboard_fnishedYn;
    private String tourboard_departure;
    private String tourboard_destination;
    private String tourboard_views;
    private List<FileInfoDto> fileInfos;
}
