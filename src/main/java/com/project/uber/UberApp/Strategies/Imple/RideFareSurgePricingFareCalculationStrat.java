package com.project.uber.UberApp.Strategies.Imple;

import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Services.DistanceService;
import com.project.uber.UberApp.Strategies.RideFareCalculationStratergy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrat implements RideFareCalculationStratergy {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;


    @Override

    public double calculateFare(RideRequest rideRequeat) {
        double distance = distanceService.calculateDistance(rideRequeat.getPickupLocation(),
                rideRequeat.getDropOfLocation());

        return distance*SURGE_FACTOR;
    }
}
