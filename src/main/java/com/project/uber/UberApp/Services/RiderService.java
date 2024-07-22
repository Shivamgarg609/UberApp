package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.User;

import java.util.List;

public interface RiderService {

    RideRequeatDTO requestRide(RideRequeatDTO rideRequestDTO);
    RideDTO cancelRide(Long rideId);



    DriverDTO rateDriver(Long rideId, Integer rating);

    RiderDTO getMyProfile();

    List<RideDTO> getAllMyRides();

    Rider createNewRider(User user);
}
