package com.welcome.enjoytrip.tourboard.model.mapper;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TourboardMapper {
    void tourboardWrite(TourBoardDto tourBoardDto);
    TourBoardDto tourboardView(int tourboard_id);
    List<TourBoardDto> tourboardList();
    void tourboardPatch(TourBoardDto tourBoardDto);
    void tourboardPut(TourBoardDto tourBoardDto);
    void tourboardDelete(int tourboard_id);

}
