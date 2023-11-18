package com.welcome.enjoytrip.tourboard.model.mapper;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.*;
@Mapper
public interface TourboardMapper {
//    add pagination
    List<TourBoardDto> listTourBoard(Map<String, Object> param) throws SQLException;

    int getTotalTourCount(Map<String, Object> param) throws SQLException;
//


    void tourboardWrite(TourBoardDto tourBoardDto);
    TourBoardDto tourboardView(int tourboard_id);
    List<TourBoardDto> tourboardList();
    void tourboardPatch(TourBoardDto tourBoardDto);
    void tourboardPut(TourBoardDto tourBoardDto);
    void tourboardDelete(int tourboard_id);
    List<TourBoardDto> getDepartureList() throws Exception;
    List<TourBoardDto> getDestinationList(String depa) throws Exception;
    List<TourBoardDto> getTourSearchList(String depa, String dest) throws Exception;

}
