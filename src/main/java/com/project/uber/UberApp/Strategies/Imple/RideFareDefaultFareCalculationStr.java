package com.project.uber.UberApp.Strategies.Imple;

import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Services.DistanceService;
import com.project.uber.UberApp.Strategies.RideFareCalculationStratergy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service


public class RideFareDefaultFareCalculationStr implements RideFareCalculationStratergy {
   private final DistanceService distanceService;

   @Override
   public double calculateFare(RideRequest rideRequeat) {
       double distance = distanceService.calculateDistance(rideRequeat.getPickupLocation(),
                            rideRequeat.getDropOfLocation());

       return distance*RIDE_FARE_MULTIPLIER;
    }



}
