package com.welcome.enjoytrip.member.model.mapper;

import com.welcome.enjoytrip.member.model.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public int join(MemberDto dto);
    public MemberDto login(MemberDto memberDto);
    public MemberDto mypage(String user_id);
    public int changePW(MemberDto memberDto);
    public int delete(String user_id);
}
