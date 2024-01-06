package com.welcome.enjoytrip.reservation.model.service;

import com.welcome.enjoytrip.location.model.LocationDto;
import com.welcome.enjoytrip.reservation.model.ReservationDto;
import com.welcome.enjoytrip.tourboard.model.TourBoardDto;

import java.util.List;

public interface ReservationService {

    void reservationWrite(ReservationDto reservationDto) throws Exception;
    List<ReservationDto> reservationList() throws Exception;
    void reservationPut(ReservationDto reservationDto) throws Exception;
    void reservationDelete(int reservation_id) throws Exception;

    List<TourBoardDto> reservationPackage(int user_id) throws Exception;

}
