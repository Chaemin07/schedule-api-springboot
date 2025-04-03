package com.example.todo.schedule;


import com.example.todo.schedule.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/schedule")
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody ScheduleRequestDto dto
    ) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(
            @PathVariable Long scheduleId,
            @RequestParam Long userId
    ) {

        ScheduleResponseDto responseDto = scheduleService.findById(userId, scheduleId);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam Long userId
    ) {
        List<ScheduleResponseDto> allSchedules = scheduleService.getAllSchedules(userId);
        return ResponseEntity.ok(allSchedules);

    }

    // 일정 수정
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequestDto dto
    ) {
        UpdateScheduleResponseDto updateScheduleResponseDto = scheduleService.updateSchedule(scheduleId, dto);
        return ResponseEntity.ok(updateScheduleResponseDto);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<DeleteScheduleResponseDto> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequestDto dto
    ) {
        DeleteScheduleResponseDto deleteScheduleResponseDto = scheduleService.deleteSchedule(scheduleId, dto);
        return ResponseEntity.ok(deleteScheduleResponseDto);
    }

}
