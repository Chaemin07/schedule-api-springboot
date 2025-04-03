package com.example.todo.auth;


import com.example.todo.auth.dto.AuthRequestDto;
import com.example.todo.auth.dto.AuthResponseDto;
import com.example.todo.user.UserService;
import com.example.todo.user.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthSessionController {

    private final UserService userService;

    /**
     * 로그인
     *
     * @return
     * @ userEmail - 로그인 ID(이메일)
     * @ userPassword - 로그인 PW(비밀번호)
     */
    @PostMapping
    public String login(
            @Valid @ModelAttribute AuthRequestDto dto,
            HttpServletRequest request
    ) {
        AuthResponseDto responseDto = userService.login(dto.getUserEmail(), dto.getUserPassword());
        Long userId = responseDto.getId();

        // 데이터 확인
//        log.info("사용자 email : {}, 사용자 비밀번호 : {}", dto.getUserEmail(), dto.getUserPassword());
        if (userId == null) {
            // 로그인 화면으로 다시가기
            return "session-login";
        }
        // 요청세션 가져오기
        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(userId);
        // 로그인 응답 dto로 변환
        AuthResponseDto authUser = new AuthResponseDto(loginUser.getId(), loginUser.getUserName());

        SessionManager.setLoginUser(session, authUser);

        return "redirect:/auth/session-home";
    }

    @PostMapping("session-logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션 삭제
            SessionManager.logout(session);
        }
        return "redirect:/auth/session-home";
    }

    @ResponseBody
    @GetMapping("/session")
    public String session(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (!SessionManager.isLogin(session)) {
            return "세션이 없습니다!";
        }
        // session 정보 조회
        log.info("session.getId()={}", session.getId());
        log.info("session.getMaxInactiveInterval()={}", session.getMaxInactiveInterval());
        log.info("session.getCreationTime()={}", session.getCreationTime());
        log.info("session.getLastAccessedTime()={}", session.getLastAccessedTime());
        log.info("session.isNew()={}", session.isNew());

        return "세션 조회 성공!";
    }
}
