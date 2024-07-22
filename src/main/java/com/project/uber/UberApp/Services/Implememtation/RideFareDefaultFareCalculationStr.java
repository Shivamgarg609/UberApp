package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Strategies.RideFareCalculationStratergy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service


public class RideFareDefaultFareCalculationStr implements RideFareCalculationStratergy {
   private final DistanceServiceOSRMimple distanceServiceOSRMimple;
    @Override

    public double calculateFare(RideRequest rideRequeat) {
          Double distance = distanceServiceOSRMimple.calculateDistance(rideRequeat.getPickupLocation(),
                  rideRequeat.getDropOfLocation());

          return distance;
    }



}
