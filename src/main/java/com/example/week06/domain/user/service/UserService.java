package com.example.week06.domain.user.service;

import com.example.week06.domain.user.dto.CheckResponse;
import com.example.week06.domain.user.dto.MypageResponse;
import com.example.week06.domain.user.dto.SignupRequest;
import com.example.week06.domain.user.entity.User;
import com.example.week06.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

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

        // 이메일 중복체크
        if(userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }
        // 닉네임 중복체크
        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");
        }
        // 비밀번호 확인 일치 여부
        if(!password.equals(signupRequest.getPasswordConfirm())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(email, nickname, encodedPassword));

        return "회원가입 처리에 성공하였습니다.";
    }

    //이메일 중복체크
    public CheckResponse emailChk(String email) {
        // 빈 값 금지
        if(email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일을 입력해주세요.");
        }

        // 이메일 형식
        String regex = "^[a-zA-Z\\d+-_.]+@[a-zA-Z\\d-]+\\.[a-zA-Z\\d-.]+$";
        if(!Pattern.matches(regex, email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일 형식이 아닙니다.");
        }

        // 이메일 중복체크
        if(userRepository.findByEmail(email).isPresent()) {
            return new CheckResponse(false);
        }
        return new CheckResponse(true);
    }

    //닉네임 중복체크
    public CheckResponse nicknameChk(String nickname) {
        // 빈 값 금지
        if(nickname.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "닉네임을 입력해주세요.");
        }

        // 닉네임 길이
        if(nickname.length() < 2  || nickname.length() > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "닉네임은 2~10자내로 입력하셔야 합니다.");
        }

        // 닉네임 중복체크
        if(userRepository.findByNickname(nickname).isPresent()) {
            return new CheckResponse(false);
        }
        return new CheckResponse(true);
    }

    public MypageResponse getUserInfo(User user) {
        return new MypageResponse(user);
    }



}