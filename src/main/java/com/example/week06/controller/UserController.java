package com.example.week06.controller;

import com.example.week06.dto.SignupRequestDto;
import com.example.week06.dto.UserInfoResponseDto;
import com.example.week06.security.UserDetailsImpl;
import com.example.week06.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    //회원가입 요청 처리
    @PostMapping("/user/signup")
    public ResponseEntity<Void> signup( @RequestBody SignupRequestDto signupRequestDto ){
        userService.createUser(signupRequestDto);
        return ResponseEntity.ok().build();
    }

    //이메일 중복 체크
    @GetMapping("/user/{email}")
    public ResponseEntity<Void> emailChk( @PathVariable String email ){
        Boolean emailChk = userService.emailChk(email);
        if(emailChk) { throw new IllegalArgumentException("이미 존재하는 이메일입니다."); }
        return ResponseEntity.ok().build();
    }

    //닉네임 중복 체크
    @GetMapping("/user/{nickname}")
    public ResponseEntity<Void> nicknameChk(@PathVariable String nickname) {
        Boolean nicknameChk = userService.nicknameChk(nickname);
        if(nicknameChk) { throw new IllegalArgumentException("이미 존재하는 닉네임입니다."); }
        return ResponseEntity.ok().build();
    }

    //회원관련 정보 받기
    @GetMapping("/user/userinfo")
    @ResponseBody
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return ResponseEntity.ok(new UserInfoResponseDto(userDetailsImpl));
    }


}