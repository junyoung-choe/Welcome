package com.welcome.enjoytrip.member.mapper;

import com.welcome.enjoytrip.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public int join(MemberDto dto);
    public MemberDto login(MemberDto memberDto);
    public MemberDto mypage(String user_id);
    public int changePW(MemberDto memberDto);
    public int delete(String user_id);
}
