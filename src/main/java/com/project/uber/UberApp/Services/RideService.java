package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequeatDTO rideRequestDTO);

    Ride createNewRide(RideRequeatDTO rideRequestDTO , Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesofRider(Long riderId, PageRequest pageRequest);

    Page<RiderDTO > getAllRidesOfDriver(Long driverID, PageRequest pageRequest);
}
