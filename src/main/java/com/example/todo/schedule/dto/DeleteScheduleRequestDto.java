package com.example.todo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteScheduleRequestDto {

    private final Long userId;
    private final String password;

}
