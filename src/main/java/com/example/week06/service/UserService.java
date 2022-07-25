package com.example.week06.service;

import com.example.week06.dto.SignupRequestDto;
import com.example.week06.model.User;
import com.example.week06.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//비밀번호 유효성 검사, 이메일 형식 검사
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        //회원 ID 중복 확인
        String email = requestDto.getEmail();
        Optional<User> userChk = userRepository.findByEmail(email);
        if(userChk.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }

        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        User user = new User(email, nickname,  password);
        userRepository.save(user);
    }
}
