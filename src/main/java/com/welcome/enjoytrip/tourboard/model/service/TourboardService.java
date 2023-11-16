package com.welcome.enjoytrip.tourboard.model.service;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import com.welcome.enjoytrip.tourboard.model.TourboardListDto;

import java.util.*;

public interface TourboardService {

    public TourboardListDto listTourboard(Map<String, String> map) throws Exception;

    public void tourboardWrite(TourBoardDto tourBoardDto) throws Exception;

    public TourBoardDto tourboardView(int tourboard_id);
    public List<TourBoardDto> tourboardList();

    public void tourboardPatch(TourBoardDto tourBoardDto);

    public void tourboardPut(TourBoardDto tourBoardDto);

    public void tourboardDelete(int tourboard_id);


}
