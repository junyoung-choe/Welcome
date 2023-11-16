package com.welcome.enjoytrip.reservation.model.mapper;

import com.welcome.enjoytrip.reservation.model.ReservationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void reservationWrite(ReservationDto reservationDto) throws Exception;
    List<ReservationDto> reservationList() throws Exception;
    void reservationPut(ReservationDto reservationDto) throws Exception;
    void reservationDelete(int reservation_id) throws Exception;

}
