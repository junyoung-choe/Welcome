package com.welcome.enjoytrip.map.model.service;

import com.welcome.enjoytrip.map.model.CodeDto;

import java.util.*;

public interface MapService {

    List<CodeDto> getSido() throws Exception;
    List<CodeDto> getGugunInSido(String sido) throws Exception;

}