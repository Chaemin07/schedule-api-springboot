package com.example.todo.user;

import com.example.todo.auth.dto.AuthResponseDto;
import com.example.todo.user.dto.DeleteUserRequestDto;
import com.example.todo.user.dto.DeleteUserResponseDto;
import com.example.todo.user.dto.UserRequestDto;
import com.example.todo.user.dto.UserResponseDto;
import com.example.todo.user.entity.UserEntity;
import com.example.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signup(UserRequestDto dto) {
        UserEntity user = new UserEntity(dto.getUserName(),
                dto.getUserEmail(),
                dto.getUserPassword());
        UserEntity savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getUserid(), savedUser.getName(),savedUser.getEmail());
    }

    public UserResponseDto findById(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isEmpty()) {
            // 204 erorr반환
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "Does not exist id : " + id);
        }
        UserEntity findUser = optionalUserEntity.get();
        return new UserResponseDto(findUser.getUserid(), findUser.getName(),findUser.getEmail());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        UserEntity findUser = userRepository.findByIdOrElseThrow(id);
        if (!oldPassword.equals(findUser.getPassword())) {
            // TODO 비밀번호 일치x 예외처리
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        findUser.updatePassword(newPassword);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getUserid(),user.getName(),user.getEmail()
                )).collect(toList());
    }

    public DeleteUserResponseDto deleteUser(Long id, DeleteUserRequestDto dto) {
        UserEntity deletedUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(deletedUser);

        return new DeleteUserResponseDto(deletedUser.getUserid(), deletedUser.getName());
    }

    // id(email), pw(password) 입력
    public AuthResponseDto login(String userEmail, String userPassword) {
        return userRepository.findByEmailAndPassword(userEmail, userPassword)
                .map(user -> new AuthResponseDto(user.getUserid(), user.getName()))
                .orElse(null);
    }

}
