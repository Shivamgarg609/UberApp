package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.SignupPDTO;
import com.project.uber.UberApp.DTO.UserDTO;

public interface AuthService {

    void login(String email, String password);

    UserDTO signup(SignupPDTO signUpDTO);

    DriverDTO onBoardNewDriver(Long userId, String vehicleId);
}
