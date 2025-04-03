package com.example.todo.schedule.repository;

import com.example.todo.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    default ScheduleEntity findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Dose not exist schedule id = " + id
                        ));
    }

    List<ScheduleEntity> findAllByUser_userid(Long userId);
}
