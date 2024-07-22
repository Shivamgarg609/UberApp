package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.SignupPDTO;
import com.project.uber.UberApp.DTO.UserDTO;
import com.project.uber.UberApp.Entities.User;
import com.project.uber.UberApp.Entities.Wallet;
import com.project.uber.UberApp.Entities.enums.Role;
import com.project.uber.UberApp.Exceptions.RuntimeConflictException;
import com.project.uber.UberApp.Services.AuthService;
import com.project.uber.UberApp.Services.RiderService;
import com.project.uber.UberApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final RiderService riderService;
    @Override
    public void login(String email, String password) {

    }

    @Override
    public UserDTO signup(SignupPDTO signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
//        TODO add wallet related service here

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId) {
        return null;
    }
}
