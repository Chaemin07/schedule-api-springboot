package com.example.todo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class UserRequestDto {
    private final String userName;
    private final String userEmail;
    private final String userPassword;
}
