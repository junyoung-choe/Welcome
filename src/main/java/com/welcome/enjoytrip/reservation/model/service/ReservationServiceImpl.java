package com.welcome.enjoytrip.reservation.model.service;

import com.welcome.enjoytrip.reservation.model.ReservationDto;
import com.welcome.enjoytrip.reservation.model.mapper.ReservationMapper;
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
}
