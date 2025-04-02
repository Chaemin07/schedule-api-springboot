package com.example.todo.user.entity;

import com.example.todo.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity(name = "user")
@Table(name = "User")
public class UserEntity extends BaseEntity {

    // PK설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    // 필드
    @Column(name = "userName", nullable = false, length = 50)
    private String name;

    @Column(name = "userPassword", nullable = false, length = 30)
    private String password;

    @Column(name = "userEmail", nullable = false, unique = true, length = 255)
    private String email;

}
