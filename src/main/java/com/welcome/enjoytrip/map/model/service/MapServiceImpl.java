package com.welcome.enjoytrip.map.model.service;

import com.welcome.enjoytrip.map.model.CodeDto;
import com.welcome.enjoytrip.map.model.mapper.MapMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService{
    private MapMapper mapMapper;

    public MapServiceImpl(MapMapper mapMapper) {
        this.mapMapper = mapMapper;
    }

    @Override
    public List<CodeDto> getSido() throws Exception {
        return mapMapper.getSido();
    }

    @Override
    public List<CodeDto> getGugunInSido(String sido) throws Exception {
        return mapMapper.getGugunInSido(sido);
    }
}
