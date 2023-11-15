package com.welcome.enjoytrip.member.model.service;

import com.welcome.enjoytrip.member.model.MemberDto;

// 로그인 회원가입 회원탈퇴 회원페이지 비밀번호변경
public interface MemberService {
    public int join(MemberDto memberDto);
    public MemberDto login(MemberDto memberDto);
    public MemberDto mypage(String user_id);
    public int changePW(MemberDto memberDto);
    public int delete(String user_id);

}
