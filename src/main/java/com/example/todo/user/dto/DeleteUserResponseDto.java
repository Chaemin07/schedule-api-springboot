package com.example.todo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteUserResponseDto {
    private final Long id;
    private final String name;

}
