package com.example.week06.domain.user.service;

import com.example.week06.domain.user.dto.CheckResponse;
import com.example.week06.domain.user.dto.MypageResponse;
import com.example.week06.domain.user.dto.SignupRequest;
import com.example.week06.domain.user.entity.User;
import com.example.week06.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입 요청 처리
    public String signup(SignupRequest signupRequest){

        String email = signupRequest.getEmail();
        String nickname = signupRequest.getNickname();
        String password = signupRequest.getPassword();

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(email, nickname, encodedPassword));

        return "회원가입 처리에 성공하였습니다.";
    }

    //이메일 중복체크
    public CheckResponse emailChk(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            return new CheckResponse(false);
        }
        return new CheckResponse(true);
    }

    //닉네임 중복체크
    public CheckResponse nicknameChk(String nickname) {
        if(userRepository.findByNickname(nickname).isPresent()) {
            return new CheckResponse(false);
        }
        return new CheckResponse(true);
    }

    public MypageResponse getUserInfo(User user) {
        return new MypageResponse(user);
    }



}