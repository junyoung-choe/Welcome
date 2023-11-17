package com.welcome.enjoytrip.tourboard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourboardListDto {

    private List<TourBoardDto> tourboards;
    private int currentPage;
    private int totalPageCount;

}
