package com.project.uber.UberApp.Strategies;

import com.project.uber.UberApp.Entities.RideRequest;

public interface RideFareCalculationStratergy {

    double RIDE_FARE_MULTIPLIER = 10.0;


    double calculateFare(RideRequest rideRequeat);
}
