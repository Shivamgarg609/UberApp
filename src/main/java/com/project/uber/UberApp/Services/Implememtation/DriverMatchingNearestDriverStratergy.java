package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Strategies.DriverMatchStratergy;
import com.project.uber.UberApp.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStratergy implements DriverMatchStratergy {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> matchMatchingDriver(RideRequest rideRequest) {

        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}