package com.example.week06.controller;


import com.example.week06.dto.SignupRequestDto;
import com.example.week06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

//반환값 프론트와 협의 필요(동작 수행 후 reload, 파일 이름 등)
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ResponseEntity registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return new ResponseEntity(HttpStatus.OK);
    }


}
