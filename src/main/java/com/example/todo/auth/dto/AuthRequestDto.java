package com.example.todo.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequestDto {

    @NotBlank(message = "아이디(email)는 필수입력입니다.")
    private final String userEmail;
    @NotBlank(message = "비밀번호는 필수입력입니다.")
    private final String userPassword;

}
