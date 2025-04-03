package com.example.todo.schedule;

import com.example.todo.schedule.dto.*;
import com.example.todo.schedule.entity.ScheduleEntity;
import com.example.todo.schedule.repository.ScheduleRepository;
import com.example.todo.user.entity.UserEntity;
import com.example.todo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;


    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        Long userId = dto.getUserId();
        UserEntity findUser = userRepository.findByIdOrElseThrow(userId);

        ScheduleEntity scheduleEntity = new ScheduleEntity(dto.getPassword(),
                dto.getTitle(),
                dto.getContents(),
                dto.getStartTime(),
                dto.getEndTime(),
                findUser
        );
        ScheduleEntity savedSchedule = scheduleRepository.save(scheduleEntity);
        return new ScheduleResponseDto(findUser.getName(),
                savedSchedule.getSchedule_id(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getStartTime(),
                savedSchedule.getEndTime());
    }


    public ScheduleResponseDto findById(Long userId, Long scheduleId) {
        String userName = findUserName(userId);
        Optional<ScheduleEntity> optionalScheduleEntity = scheduleRepository.findById(scheduleId);
        if (optionalScheduleEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "Dose not exist schedule id = " + scheduleId
            );
        }
        ScheduleEntity findSchedule = optionalScheduleEntity.get();
        return new ScheduleResponseDto(userName, findSchedule.getSchedule_id(),
                findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getStartTime(),
                findSchedule.getEndTime());
    }

    private String findUserName(Long userId) {
        UserEntity findUser = userRepository.findByIdOrElseThrow(userId);
        return findUser.getName();
    }

    public List<ScheduleResponseDto> getAllSchedules(Long userId) {
        List<ScheduleEntity> schedules = scheduleRepository.findAllByUser_userid(userId);
        return schedules.stream()
                .map(ScheduleResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeleteScheduleResponseDto deleteSchedule(Long scheduleId, DeleteScheduleRequestDto dto) {
        // 유저 체크
        UserEntity findUser = userRepository.findByIdOrElseThrow(dto.getUserId());
        // 비밀번호 체크
        if (!findUser.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        // 삭제할 일정 조회
        ScheduleEntity deletedSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다!"));

        scheduleRepository.delete(deletedSchedule);
        return new DeleteScheduleResponseDto(true, scheduleId + " 일정이 삭제되었습니다.", scheduleId);

    }

    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleRequestDto dto) {
        // 유저 체크
        UserEntity findUser = userRepository.findByIdOrElseThrow(dto.getUserId());
        // 비밀번호 체크
        if (!findUser.getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        // 수정할 일정 조회
        ScheduleEntity updateSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다!"));
        if (!updateSchedule.getUser().getUserid().equals(dto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "해당 사용자의 일정이 아닙니다.");
        }

        // 일정 수정
        updateSchedule.update(dto.getTitle(), dto.getContents(), dto.getStartTime(), dto.getEndTime());
        return new UpdateScheduleResponseDto(scheduleId, "일정이 수정되었습니다.");
    }
}
