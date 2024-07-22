package com.project.uber.UberApp.Strategies;

import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.Entities.RideRequest;

public interface RideFareCalculationStratergy {

    double calculateFare(RideRequest rideRequeat);
}
