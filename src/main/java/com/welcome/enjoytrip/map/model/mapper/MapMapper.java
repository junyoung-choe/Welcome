package com.welcome.enjoytrip.map.model.mapper;

import com.welcome.enjoytrip.map.model.CodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface MapMapper {

    List<CodeDto> getSido() throws SQLException;
    List<CodeDto> getGugunInSido(String sido) throws SQLException;

}
