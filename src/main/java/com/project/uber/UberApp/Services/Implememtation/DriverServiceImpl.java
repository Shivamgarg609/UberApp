package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Entities.enums.RideRequestStatus;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Services.*;
import com.project.uber.UberApp.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;

    private final DriverRepository driverRepository;

    private final RideService rideService;

    private final ModelMapper modalMapper;

    private final PaymentService paymentService;
    @Override
    @Transactional
    public RideDTO acceptRide(Long rideId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideId);

        if(rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request cant be accepted, status is "+ rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();

        if(currentDriver.isAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        Driver savedDriver = updateDriverAvailability(currentDriver, false);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modalMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled Ride is "+ ride.getRideStatus());
        }

        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);

        return modalMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideID ,  String otp) {
       Ride ride = rideService.getRideById(rideID);
       Driver driver = getCurrentDriver();

       if(!driver.equals(ride.getDriver())){
           throw new RuntimeException("Driver cannot start ride");
       }

       if(ride.getRideStatus().equals(RideStatus.CONFIRMED)){
           throw new RuntimeException("Ride status is not confirmed so cannot start ride as status is"+ ride.getRideStatus());
       }

       if(!otp.equals(ride.getOTP())){
           throw new RuntimeException("Otp is not valid");
       }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        paymentService.createNewPayment(savedRide);
        return modalMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public RideDTO endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cannot start ride");
        }

        if(ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride status is not ONGOING so cannot be ended"+ ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ENDED);
        updateDriverAvailability(driver,true);
        paymentService.processPayment(ride);

        return modalMapper.map(savedRide,RideDTO.class);
    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDTO getMyProfile() {
        Driver currentDriver  = getCurrentDriver();
        return modalMapper.map(currentDriver, DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver, pageRequest).map(
                ride -> modalMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    public Driver getCurrentDriver(){
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found with " +
                "id "+2));
    }
}
