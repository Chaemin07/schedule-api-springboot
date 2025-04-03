package com.example.todo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class UserResponseDto {
    private final Long id;

    private final String userName;

    private final String email;

}
