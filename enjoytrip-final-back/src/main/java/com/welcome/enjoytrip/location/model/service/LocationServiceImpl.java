package com.welcome.enjoytrip.location.model.service;

import com.welcome.enjoytrip.location.model.LocationDto;
import com.welcome.enjoytrip.location.model.mapper.LocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    @Override
    public void locationWrite(LocationDto locationDto) throws Exception {
        locationMapper.locationWrite(locationDto);
    }

    @Override
    public List<LocationDto> locationList() throws Exception {
        return locationMapper.locationList();
    }

    @Override
    public void locationPut(LocationDto locationDto) throws Exception {
        locationMapper.locationPut(locationDto);
    }

    @Override
    public void locationDelete(int location_id) throws Exception {
        locationMapper.locationDelete(location_id);
    }
}
