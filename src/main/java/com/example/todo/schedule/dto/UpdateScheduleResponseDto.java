package com.example.todo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleResponseDto {
    private Long scheduleId;
    private String message;
}
