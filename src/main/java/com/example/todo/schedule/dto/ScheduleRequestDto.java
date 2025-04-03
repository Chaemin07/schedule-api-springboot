package com.example.todo.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    @NotBlank
    private Long userId;
    @NotBlank
    private String password;
    private String title;
    private String contents;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
