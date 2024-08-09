package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.enums.RideRequestStatus;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Services.RideRequestService;
import com.project.uber.UberApp.Services.RideService;
import com.project.uber.UberApp.repositories.RideRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImple implements RideService {

    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {

        return rideRepository.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("Ride not found"));
    }

    @Override
    public void matchWithDrivers(RideRequest rideRequest) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {

        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOTP(generateOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesofRider(Rider rider, PageRequest pageRequest) {

        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {

        return rideRepository.findByDriver(driver,pageRequest);
    }

    private String generateOTP(){

        Random random = new Random();
        int OTPint = random.nextInt(10000);

        return String.format("%04d" , OTPint);
    }
}
