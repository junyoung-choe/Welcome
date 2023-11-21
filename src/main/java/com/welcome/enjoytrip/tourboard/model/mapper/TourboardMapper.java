package com.welcome.enjoytrip.tourboard.model.mapper;

import com.welcome.enjoytrip.tourboard.model.FileInfoDto;
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
    List<TourBoardDto> getPopularList() throws Exception;
    List<TourBoardDto> getCheapList() throws Exception;

    List<FileInfoDto> fileInfoList(int tourboard_id);

    //////// ㅍㅏ 일 ////////
    // 파일을 등록할때는 service 에서 패키지 등록 파일 등록 둘다 호출 !
    // 파일을 읽어 올때는 tourboardView 할때 같이 불러온다 (myBatis 내부에서) -> 파일을 저장해놓는다 (vue 에서 onMount 로 사진을 띄운다 )
    // Vue 에서 파일 저장 경로를 지정해서 파일을 물리적으로 저장한다 !
    // get 할때는 파일의 경로를 불러가서 화면에 띄운다 !

    void registerFile(TourBoardDto tourBoardDto) throws Exception;

//    void deleteFile(int articleNo) throws Exception;
    void plus(Map<String, Integer> map) throws Exception;
}
