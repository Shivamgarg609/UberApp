package com.project.uber.UberApp.Strategies.Imple;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Strategies.DriverMatchStratergy;
import com.project.uber.UberApp.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DriverMatchHighestRatedDriverStratergy implements DriverMatchStratergy {

   private final DriverRepository driverRepository;
    @Override
    public List<Driver> matchMatchingDriver(RideRequest rideRequest) {

        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
