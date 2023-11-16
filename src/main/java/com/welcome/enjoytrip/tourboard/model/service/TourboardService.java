package com.welcome.enjoytrip.tourboard.model.service;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;

import java.util.List;

public interface TourboardService {

    public void tourboardWrite(TourBoardDto tourBoardDto);

    public TourBoardDto tourboardView(int tourboard_id);
    public List<TourBoardDto> tourboardList();

    public void tourboardPatch(TourBoardDto tourBoardDto);

    public void tourboardPut(TourBoardDto tourBoardDto);

    public void tourboardDelete(int tourboard_id);


}
