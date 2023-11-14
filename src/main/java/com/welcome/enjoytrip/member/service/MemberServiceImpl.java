package com.welcome.enjoytrip.member.service;

import com.welcome.enjoytrip.member.dto.MemberDto;
import com.welcome.enjoytrip.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// mapper 호출
// 로그인 회원가입 회원탈퇴 회원페이지 비밀번호변경
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
       this.memberMapper = memberMapper;
    }


    @Override
    public int join(MemberDto memberDto) {
        memberMapper.join(memberDto);
        return 1;
    }

    @Override
    public MemberDto login(MemberDto memberDto) {
        return memberMapper.login(memberDto);
    }

    @Override
    public MemberDto mypage(String user_id) {
        return memberMapper.mypage(user_id);
    }

    @Override
    public int changePW(MemberDto memberDto) {
        memberMapper.changePW(memberDto);
        return 1;
    }

    @Override
    public int delete(String user_id) {
        memberMapper.delete(user_id);
        return 1;
    }
}
