package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import com.project.uber.UberApp.Services.RideService;
import com.project.uber.UberApp.Services.RiderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImple implements RideService {
    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequeatDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequeatDTO rideRequestDTO, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesofRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<RiderDTO> getAllRidesOfDriver(Long driverID, PageRequest pageRequest) {
        return null;
    }
}
