package com.example.todo.schedule.dto;


import com.example.todo.schedule.entity.ScheduleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String userName;
    private Long scheduleId;
    private String title;
    private String contents;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static ScheduleResponseDto fromEntity(ScheduleEntity entity) {
        if (entity.getUser() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 사용자는 없습니다.");
        }
        return new ScheduleResponseDto(
                entity.getUser().getName(),
                entity.getSchedule_id(),
                entity.getTitle(),
                entity.getContents(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
}
