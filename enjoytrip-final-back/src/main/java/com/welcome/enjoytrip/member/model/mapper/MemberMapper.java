package com.welcome.enjoytrip.member.model.mapper;

import com.welcome.enjoytrip.member.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {
    public int join(MemberDto dto);
    public MemberDto login(MemberDto memberDto);
    public MemberDto mypage(String user_id);
    public int changePW(MemberDto memberDto);
    public int delete(String user_id);

    //jwt
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    Object getRefreshToken(String userid) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
