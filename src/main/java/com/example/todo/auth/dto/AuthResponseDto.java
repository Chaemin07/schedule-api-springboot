package com.example.todo.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponseDto {

    private final Long id;

    private final String userName;

    public AuthResponseDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
