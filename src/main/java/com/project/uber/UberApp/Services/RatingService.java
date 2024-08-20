package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Ride;


public interface RatingService {

    RiderDTO rateRider(Ride ride, Integer rating);

    DriverDTO rateDriver(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
