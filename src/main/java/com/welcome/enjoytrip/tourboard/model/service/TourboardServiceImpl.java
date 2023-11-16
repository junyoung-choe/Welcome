package com.welcome.enjoytrip.tourboard.model.service;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import com.welcome.enjoytrip.tourboard.model.mapper.TourboardMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourboardServiceImpl implements TourboardService {

    private final TourboardMapper tourboardMapper;

    public TourboardServiceImpl(TourboardMapper tourboardMapper) {
        this.tourboardMapper = tourboardMapper;
    }

    @Override
    public void tourboardWrite(TourBoardDto tourBoardDto) {
        tourboardMapper.tourboardWrite(tourBoardDto);
    }

    @Override
    public TourBoardDto tourboardView(int tourboard_id) {
        return tourboardMapper.tourboardView(tourboard_id);
    }

    @Override
    public List<TourBoardDto> tourboardList() {
        return tourboardMapper.tourboardList();
    }

    @Override
    public void tourboardPatch(TourBoardDto tourBoardDto) {
        tourboardMapper.tourboardPatch(tourBoardDto);
    }

    @Override
    public void tourboardPut(TourBoardDto tourBoardDto) {
        tourboardMapper.tourboardPut(tourBoardDto);
    }

    @Override
    public void tourboardDelete(int tourboard_id) {
        tourboardMapper.tourboardDelete(tourboard_id);
    }


}
