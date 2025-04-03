package com.example.todo.user.repository;

import com.example.todo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    default UserEntity findByIdOrElseThrow(Long id) {

        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Dose not exist id = " + id
                        ));
    }

    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
