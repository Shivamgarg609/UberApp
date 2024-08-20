package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.*;
import com.project.uber.UberApp.Entities.enums.RideRequestStatus;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Services.DriverService;
import com.project.uber.UberApp.Services.RatingService;
import com.project.uber.UberApp.Services.RideService;
import com.project.uber.UberApp.Services.RiderService;

import com.project.uber.UberApp.Strategies.RideStratergyManager;
import com.project.uber.UberApp.repositories.RideRequestRepository;
import com.project.uber.UberApp.repositories.RiderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImple implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStratergyManager rideStratergyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private  final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequeatDTO requestRide(RideRequeatDTO rideRequestDTO) {

        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO,RideRequest.class);
         rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
         rideRequest.setRider(rider);

         Double fare = rideStratergyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
         rideRequest.setFare(fare);

         RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
         List <Driver> drivers = rideStratergyManager.driverMatchingStrategy(rider.getRating()).matchMatchingDriver(rideRequest);
         return modelMapper.map(savedRideRequest,RideRequeatDTO.class);
    }


    @Override
    public RideDTO cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride =   rideService.getRideById(rideId);

        if(rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled Ride is "+ ride.getRideStatus());
        }

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getDriver())){
            throw new RuntimeException("Rider cannot rate the rider");
        }

        if(ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ended so cannot rate the rider "+ ride.getRideStatus());
        }
        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public RiderDTO getMyProfile() {
        Rider currentRider = getCurrentRider();
        return  modelMapper.map(currentRider,RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesofRider(currentRider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)

        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().
                user(user).rating(0.0).name("").build();

        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("" +
                "Rider not found"));
    }




}
