package com.example.todo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteScheduleResponseDto {
    private boolean success;
    private String message;
    private Long scheduleId;

}
