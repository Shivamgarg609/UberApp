package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.SignupPDTO;
import com.project.uber.UberApp.DTO.UserDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.User;
import com.project.uber.UberApp.Entities.enums.Role;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Exceptions.RuntimeConflictException;
import com.project.uber.UberApp.Services.AuthService;
import com.project.uber.UberApp.Services.DriverService;
import com.project.uber.UberApp.Services.RiderService;
import com.project.uber.UberApp.Services.WalleteService;
import com.project.uber.UberApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.project.uber.UberApp.Entities.enums.Role.DRIVER;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final WalleteService walletService;
    private final RiderService riderService;

    private final DriverService driverService;
    @Override
    public void login(String email, String password) {

    }

    @Override
    @Transactional
    public UserDTO signup(SignupPDTO signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(()-> new ResourceNotFoundException("User not found with id" + userId));

        if(user.getRoles().contains(DRIVER))
            throw new RuntimeException("User with id" + userId+" is already a Driver");

        Driver createDriver =  Driver.builder()
                .user(user)
                .vehichleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(DRIVER);
        userRepository.save(user);
       Driver savedDriver =  driverService.createNewDriver(createDriver);
       return modelMapper.map(savedDriver,DriverDTO.class);
    }
}
