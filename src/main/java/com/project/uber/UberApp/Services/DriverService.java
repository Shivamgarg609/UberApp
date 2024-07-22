package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RiderDTO;

import java.util.List;

public interface DriverService {

    RiderDTO acceptRide(Long rideId);
    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideID);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    List<RideDTO> getAllMyRides();


}
