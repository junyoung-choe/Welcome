package com.welcome.enjoytrip.member.controller;

import com.welcome.enjoytrip.member.model.MemberDto;
import com.welcome.enjoytrip.member.model.service.MemberService;
import com.welcome.enjoytrip.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Slf4j
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Autowired
    public MemberController(MemberService memberService, JWTUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
        Map<String, Object> map = new HashMap<>();
//        try {
//            MemberDto login = memberService.login(memberDto);
//            map.put("resmsg", "로그인 성공");
//            map.put("Id", login.getUser_id());
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("resmsg", "로그인 실패");
//            map.put("resdata", null);
//        }
//        ResponseEntity<Map<String, Object>> res = new ResponseEntity(map, HttpStatus.OK);
//        return res;

        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            MemberDto loginUser = memberService.login(memberDto);
            if(loginUser != null) {
                String accessToken = jwtUtil.createAccessToken(loginUser.getUser_id());
                String refreshToken = jwtUtil.createRefreshToken(loginUser.getUser_id());
                log.debug("access token : {}", accessToken);
                log.debug("access token : {}", accessToken);

//				발급받은 refresh token을 DB에 저장.
                memberService.saveRefreshToken(loginUser.getUser_id(), refreshToken);

//				JSON으로 token 전달.
                map.put("access-token", accessToken);
                map.put("refresh-token", refreshToken);

                status = HttpStatus.CREATED;
            } else {
                map.put("message", "아이디 또는 패스워드를 확인해주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }

        } catch (Exception e) {
            log.debug("로그인 에러 발생 : {}", e);
            map.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(map, status);
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

    // 토큰이 유효한지 확인하고 회원의 dto 를 가져온다
    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("userId") String user_id,
            HttpServletRequest request) {
//		logger.debug("user_id : {} ", user_id);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                MemberDto memberDto = memberService.mypage(user_id);
                resultMap.put("userInfo", memberDto);
//                log.info(memberDto.toString());
                status = HttpStatus.OK;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // DB 에 저장되어 있는 refreshToken 을 제거한다. -> 로그아웃 해버린다
    @GetMapping("/logout/{userId}")
    public ResponseEntity<?> removeToken(@PathVariable ("userId") String user_id) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            memberService.deleRefreshToken(user_id);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);

    }

    // refresh 토큰을 확인하고 아직 유효하다면 access 토큰을 재발급 해준다
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refreshToken");
        log.debug("token : {}, memberDto : {}", token, memberDto);
        if (jwtUtil.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(memberDto.getUser_id()))) {
                String accessToken = jwtUtil.createAccessToken(memberDto.getUser_id());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 액세스토큰 재발급!!!");
                resultMap.put("access-token", accessToken);
                status = HttpStatus.CREATED;
            }
        } else {
            log.debug("리프레쉬토큰도 사용불가!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
            // 즉 리프레쉬 토근까지 만료 된것이다 !
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
