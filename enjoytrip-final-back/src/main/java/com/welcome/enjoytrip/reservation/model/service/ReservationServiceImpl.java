package com.welcome.enjoytrip.reservation.model.service;

import com.welcome.enjoytrip.reservation.model.ReservationDto;
import com.welcome.enjoytrip.reservation.model.mapper.ReservationMapper;
import com.welcome.enjoytrip.tourboard.model.FileInfoDto;
import com.welcome.enjoytrip.tourboard.model.TourBoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    @Override
    public void reservationWrite(ReservationDto reservationDto) throws Exception {
        reservationMapper.reservationWrite(reservationDto);
    }

    @Override
    public List<ReservationDto> reservationList() throws Exception {
        return reservationMapper.reservationList();
    }

    @Override
    public void reservationPut(ReservationDto reservationDto) throws Exception {
        reservationMapper.reservationPut(reservationDto);
    }

    @Override
    public void reservationDelete(int reservation_id) throws Exception {
        reservationMapper.reservationDelete(reservation_id);
    }

    @Override
    public List<TourBoardDto> reservationPackage(int user_id) throws Exception {
        List<TourBoardDto> list = reservationMapper.reservationPackage(user_id);
        for(TourBoardDto tourBoardDto : list) {
            List<FileInfoDto> files = reservationMapper.fileInfoList((int) tourBoardDto.getTourboard_id());
            tourBoardDto.setFileInfos(files);
        }
        return list;
    }
}
