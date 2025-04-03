package com.example.todo.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateScheduleRequestDto {
    private Long userId;
    private String password;

    private String title;
    private String contents;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
