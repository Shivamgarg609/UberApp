package com.project.uber.UberApp.Strategies;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.RideRequest;

import java.util.List;

public interface DriverMatchStratergy {

     List<Driver> matchMatchingDriver(RideRequest rideRequest);
}
