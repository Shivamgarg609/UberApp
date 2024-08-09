package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Services.RideRequestService;
import com.project.uber.UberApp.repositories.RideRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RideRequestServiceImpl implements RideRequestService {

    private RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride request not found"));
    }

    @Override
    public void update(RideRequest rideRequest) {

       RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
               .orElseThrow(() -> new ResourceNotFoundException("Ride request ot found with id"));

       rideRequestRepository.save(rideRequest);
    }
}
