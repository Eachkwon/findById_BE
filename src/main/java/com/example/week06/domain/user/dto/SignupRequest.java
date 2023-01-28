package com.example.week06.domain.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignupRequest {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
    
    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, max=10, message = "닉네임을 2~10자 내로 입력해주세요.")
    private String nickname;
    
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp= "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 영문, 숫자 및 특수문자를 포함한 8자 이상이어야 합니다.")
    private String password;
    
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String passwordConfirm;

}