package com.welcome.enjoytrip.tourboard.model.service;

import com.welcome.enjoytrip.tourboard.model.FileInfoDto;
import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import com.welcome.enjoytrip.tourboard.model.TourboardListDto;
import com.welcome.enjoytrip.tourboard.model.mapper.TourboardMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TourboardServiceImpl implements TourboardService {

    private final TourboardMapper tourboardMapper;

    public TourboardServiceImpl(TourboardMapper tourboardMapper) {
        this.tourboardMapper = tourboardMapper;
    }

    @Override
    public List<TourBoardDto> listTourboard(Map<String, String> map) throws Exception {
        System.out.println("===========");
        System.out.println(map);
        System.out.println("===========");
        Map<String, Object> param = new HashMap<>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
//        if ("user_id".equals(key))
//            param.put("key", key == null ? "" : "b.user_id");


        /// file 같이 가져오기
        List<TourBoardDto> list = tourboardMapper.listTourBoard(param);
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = tourboardMapper.fileInfoList((int)tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
//            System.out.println("확인");
//            System.out.println(files);
        }
        return list;

//        int totalTourCount = tourboardMapper.getTotalTourCount(param);
//        int totalPageCount = (totalTourCount - 1) / sizePerPage + 1;
//
//        TourboardListDto tourboardListDto = new TourboardListDto();
//        tourboardListDto.setTourboards(list);
//        tourboardListDto.setCurrentPage(currentPage);
//        tourboardListDto.setTotalPageCount(totalPageCount);
//        return tourboardListDto;

    }

    @Override
    public void tourboardWrite(TourBoardDto tourBoardDto) throws Exception {
        tourboardMapper.tourboardWrite(tourBoardDto);
        if(tourBoardDto.getFileInfos() != null) {
            tourboardMapper.registerFile(tourBoardDto);
        }
    }

    @Override
    public TourBoardDto tourboardView(int tourboard_id) {
        TourBoardDto tourBoardDto;
        tourBoardDto = tourboardMapper.tourboardView(tourboard_id);
        tourBoardDto.setFileInfos(tourboardMapper.fileInfoList(tourboard_id));
        return tourBoardDto;
    }

    @Override
    public List<TourBoardDto> tourboardList() {
        List<TourBoardDto> list = tourboardMapper.tourboardList();
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = tourboardMapper.fileInfoList((int)tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
            System.out.println("확인");
            System.out.println(files);
        }
        return list;
    }

    @Override
    public List<TourBoardDto> tourboardViewUserId(int user_id) {
        List<TourBoardDto> list = tourboardMapper.tourboardViewUserId(user_id);
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = tourboardMapper.fileInfoList((int) tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
        }
        return list;
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

    @Override
    public List<TourBoardDto> getDepartureList() throws Exception {
        return tourboardMapper.getDepartureList();
    }

    @Override
    public List<TourBoardDto> getDestinationList(String depa) throws Exception {
        return tourboardMapper.getDestinationList(depa);
    }

    @Override
    public List<TourBoardDto> getTourSearchList(String depa, String dest) throws Exception {
        List<TourBoardDto> list = tourboardMapper.getTourSearchList(depa, dest);
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = tourboardMapper.fileInfoList((int) tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
        }

        return list;
    }

    @Override
    public List<TourBoardDto> getPopularList() throws Exception {
        List<TourBoardDto> list = tourboardMapper.getPopularList();
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = tourboardMapper.fileInfoList((int) tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
        }
        return list;
    }

    @Override
    public List<TourBoardDto> getCheapList() throws Exception {
        List<TourBoardDto> list = tourboardMapper.getCheapList();
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = tourboardMapper.fileInfoList((int) tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
        }
        return list;
    }

    @Override
    public void plus(int tourboard_id, int reserveNum) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("tourboard_id", tourboard_id);
        map.put("reserveNum", reserveNum);
        tourboardMapper.plus(map);
    }

    @Override
    public List<String> getDestinationForGPT() throws Exception {
        List<String> list = tourboardMapper.getDestinationForGPT();
        return list;
    }


}
