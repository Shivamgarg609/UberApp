package com.project.uber.UberApp.Controller;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.OnBoardDriverDTO;
import com.project.uber.UberApp.DTO.SignupPDTO;
import com.project.uber.UberApp.DTO.UserDTO;
import com.project.uber.UberApp.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDTO> signUp(@RequestBody SignupPDTO signupDto) {
        return new ResponseEntity<>(authService.signup(signupDto),HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDTO> onBoardDriver(@PathVariable Long userId, @RequestBody OnBoardDriverDTO onBoardDriverDTO){
        return ResponseEntity.ok(authService.onBoardNewDriver(userId,onBoardDriverDTO.getVehicleId()));
    }



}
