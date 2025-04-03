package com.example.todo.user;


import com.example.todo.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto dto) {
        // 이름, 이메일, 비밀번호 입력
        UserResponseDto signupResponseDto = userService.signup(dto);
        return new ResponseEntity<>(signupResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    // 유저 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto responseDto = userService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 유저 비밀번호 수정
    @PatchMapping("/{id}/password")
    public ResponseEntity<MessageResponseDto> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto dto
    ) {
        userService.updatePassword(id, dto.getOldPassword(), dto.getNewPassword());

        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK.value(), "비밀번호가 변경되었습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> deleteUser(
            @PathVariable Long id,
            @RequestBody DeleteUserRequestDto dto
            ) {
        DeleteUserResponseDto responseDto = userService.deleteUser(id, dto);
        return ResponseEntity.ok(
                new MessageResponseDto(HttpStatus.OK.value(),
                "id ( "+ responseDto.getId() + " ) " + responseDto.getName() + "이 삭제되었습니다." )
        );

    }
}
