package com.example.todo.auth;


import com.example.todo.auth.dto.AuthResponseDto;
import com.example.todo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SessionHomeController {
    private final UserService userService;

    @GetMapping("/session-home")
    public String sessionHome(@SessionAttribute(name = SessionManager.LOGIN_USER, required = false) AuthResponseDto loginUser,
                              Model model) {

        if (loginUser == null) {
            return "session-login";
        }
        model.addAttribute("loginUser", loginUser);
        return "session-home";
    }
}
