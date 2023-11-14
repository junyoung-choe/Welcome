package com.welcome.enjoytrip.member.controller;

import com.welcome.enjoytrip.member.dto.MemberDto;
import com.welcome.enjoytrip.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@ResponseBody
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            MemberDto login = memberService.login(memberDto);
            map.put("resmsg", "로그인 성공");
            map.put("Id", login.getUser_id());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "로그인 실패");
            map.put("resdata", null);
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> MemberJoin(@RequestBody MemberDto memberDto) {
        Map<String, Object> map = new HashMap<>();
        try {
            int res = memberService.join(memberDto);
            map.put("resmsg", "입력 성공");
            map.put("resdata", res);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "입력실패");
            map.put("resdata", e.getMessage());
        }

        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
        return res;
    }

    @GetMapping("/mypage/{id}")
    public ResponseEntity<Map<String, Object>> mypage(@PathVariable("id") String id) {
        MemberDto memberDto = new MemberDto();
        Map<String, Object> map = new HashMap<>();

        try {
            MemberDto member = memberService.mypage(id);
            map.put("resdata", member);
            map.put("resmsg", "조회성공");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("resmsg", "조회실패");
            map.put("resdata", e.getMessage());
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map, HttpStatus.OK);
        return res;
    }

    @PutMapping("/changePW")
    public ResponseEntity<Map<String, Object>> changePW(@RequestBody MemberDto dto) {
        Map<String, Object> map = new HashMap();
        try {
            int res = memberService.changePW(dto);
            map.put("resdata", res);
            map.put("resmsg", "변경성공");
        } catch (Exception e) {
            map.put("resmsg", "변경실패");
            map.put("resdata", e.getMessage());
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map, HttpStatus.OK);
        return res;
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> MemberDelete(@PathVariable("id") String id) {
        Map<String, Object> map = new HashMap();
        MemberDto memberDto = new MemberDto();
        try {
            int res = memberService.delete(id);
            map.put("resdata", res);
            map.put("resmsg", "삭제성공");

        } catch (Exception e) {
            map.put("resmsg", "수정실패");
            map.put("resdata", e.getMessage());
        }
        ResponseEntity<Map<String, Object>> res = new ResponseEntity<>(map, HttpStatus.OK);
        return res;
    };

}
