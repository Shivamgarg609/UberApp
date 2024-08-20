package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {

    RideDTO acceptRide(Long rideId);
    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideID , String otp);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);
}
