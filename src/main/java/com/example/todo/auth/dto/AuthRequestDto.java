package com.example.todo.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequestDto {

    private final String userName;
    private final String userEmail;
    private final String userPassword;

    public AuthRequestDto(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
