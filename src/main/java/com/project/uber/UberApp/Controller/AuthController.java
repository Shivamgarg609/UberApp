package com.project.uber.UberApp.Controller;

import com.project.uber.UberApp.DTO.SignupPDTO;
import com.project.uber.UberApp.DTO.UserDTO;
import com.project.uber.UberApp.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDTO signUp(@RequestBody SignupPDTO signupDto) {
        return authService.signup(signupDto);
    }

}
