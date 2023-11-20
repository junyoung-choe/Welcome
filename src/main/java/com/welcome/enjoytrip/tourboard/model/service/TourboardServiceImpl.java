package com.welcome.enjoytrip.tourboard.model.service;

import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import com.welcome.enjoytrip.tourboard.model.TourboardListDto;
import com.welcome.enjoytrip.tourboard.model.mapper.TourboardMapper;
import org.springframework.stereotype.Service;

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
        List<TourBoardDto> list = tourboardMapper.listTourBoard(param);

        return list;
    }

    @Override
    public void tourboardWrite(TourBoardDto tourBoardDto) throws Exception {
        tourboardMapper.tourboardWrite(tourBoardDto);
        tourboardMapper.registerFile(tourBoardDto);
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
        return tourboardMapper.getTourSearchList(depa, dest);
    }

    @Override
    public List<TourBoardDto> getPopularList() throws Exception {
        return tourboardMapper.getPopularList();
    }

    @Override
    public List<TourBoardDto> getCheapList() throws Exception {
        return tourboardMapper.getCheapList();
    }


}
