package com.example.week06.service;

import com.example.week06.dto.SignupRequestDto;
import com.example.week06.model.User;
import com.example.week06.repository.UserRepository;
import com.example.week06.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입 요청 처리
    public User createUser(SignupRequestDto signupRequestDto){
        User user = signupRequestDto.createUser();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("Can't find " + email));
        return new UserDetailsImpl(user);
    }

    //이메일 중복체크
    public Boolean emailChk(String email) throws UsernameNotFoundException {
        return userRepository.existsByEmail(email);
    }

    //닉네임 중복체크
    public Boolean nicknameChk(String nickname) throws IllegalArgumentException {
        return userRepository.existsByNickname(nickname);
    }



}