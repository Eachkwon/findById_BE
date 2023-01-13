package com.example.week06.domain.user.controller;

import com.example.week06.domain.user.dto.SignupRequest;
import com.example.week06.global.security.UserDetailsImpl;
import com.example.week06.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 요청 처리
    @PostMapping("/api/signup")
    public ResponseEntity<Void> signup( @RequestBody SignupRequest signupRequest){
        userService.signup(signupRequest);
        return ResponseEntity.ok().build();
    }

    //이메일 중복 체크
    @PostMapping("/api/email-check")
    public ResponseEntity<?> emailChk(@RequestBody Map<String,String> map ){
        return ResponseEntity.ok(userService.emailChk(map.get("email")));
    }

    //닉네임 중복 체크
    @PostMapping("/user/nickname-check")
    public ResponseEntity<?> nicknameChk(@RequestBody Map<String, String> map) {
        return ResponseEntity.ok(userService.nicknameChk(map.get("nickname")));
    }

    //회원관련 정보 받기
    @GetMapping("/api/mypage")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(userService.getUserInfo(userDetails.getUser()));
    }

}
