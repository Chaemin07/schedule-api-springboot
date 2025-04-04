package com.example.todo.schedule.entity;


import com.example.todo.common.entity.BaseEntity;
import com.example.todo.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity(name = "schedule")
@Table(name = "Schedule")
@Getter
@NoArgsConstructor
public class ScheduleEntity extends BaseEntity {

    // PK 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long schedule_id;

    // 필드 설정
    @Column(name = "user_password", nullable = false, length = 30)
    @NotBlank
    private String password;

    @Column(name = "schedule_title", nullable = false, length = 100)
    @NotBlank
    private String title;

    @Lob
    @Column(name = "schedule_contents")
    private String contents;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private UserEntity user;

    public ScheduleEntity(String password,
                          String title,
                          String contents,
                          LocalDateTime startTime,
                          LocalDateTime endTime,
                          UserEntity user) {
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }
    public void update(String title, String contents, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.contents = contents;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
