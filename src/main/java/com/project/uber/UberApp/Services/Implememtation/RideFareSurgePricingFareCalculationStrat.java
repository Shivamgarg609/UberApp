package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Strategies.RideFareCalculationStratergy;

public class RideFareSurgePricingFareCalculationStrat implements RideFareCalculationStratergy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
