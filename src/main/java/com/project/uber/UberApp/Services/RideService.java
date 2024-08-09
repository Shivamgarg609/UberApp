package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequest rideRequest);

    Ride createNewRide(RideRequest rideRequest , Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesofRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

 //   Page<Ride> getAllRidesOfRider(Rider currentRider, PageRequest pageRequest);
}
