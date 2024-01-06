package com.welcome.enjoytrip.location.model.mapper;

import com.welcome.enjoytrip.location.model.LocationDto;

import java.util.List;

public interface LocationMapper {
    void locationWrite(LocationDto locationDto) throws Exception;
    List<LocationDto> locationList() throws Exception;
    void locationPut(LocationDto locationDto) throws Exception;
    void locationDelete(int location_id) throws Exception;
}
