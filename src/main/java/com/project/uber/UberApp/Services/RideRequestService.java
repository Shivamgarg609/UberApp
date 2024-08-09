package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.Entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
