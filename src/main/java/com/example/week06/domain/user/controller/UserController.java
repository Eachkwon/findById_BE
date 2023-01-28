package com.example.week06.domain.user.controller;

import com.example.week06.domain.user.dto.SignupRequest;
import com.example.week06.global.common.SuccessResponse;
import com.example.week06.global.security.UserDetailsImpl;
import com.example.week06.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입 요청 처리
    @PostMapping("/api/signup")
    public ResponseEntity<?> signup( @RequestBody @Valid SignupRequest signupRequest){
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, userService.signup(signupRequest)));
    }

    //이메일 중복 체크
    @PostMapping("/api/email-check")
    public ResponseEntity<?> emailChk(@RequestBody Map<String,String> map ){
        String msg = "이메일 중복체크 결과를 확인해주세요.";
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, msg, userService.emailChk(map.get("email"))));
    }

    //닉네임 중복 체크
    @PostMapping("/user/nickname-check")
    public ResponseEntity<?> nicknameChk(@RequestBody Map<String, String> map) {
        String msg = "닉네임 중복체크 결과를 확인해주세요.";
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, msg, userService.nicknameChk(map.get("nickname"))));
    }

    //회원관련 정보 받기
    @GetMapping("/api/mypage")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        String msg = "회원정보 조회에 성공하였습니다.";
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, msg, userService.getUserInfo(userDetails.getUser())));
    }

}
