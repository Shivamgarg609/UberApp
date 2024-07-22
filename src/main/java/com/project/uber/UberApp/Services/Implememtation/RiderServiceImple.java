package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RideDTO;
import com.project.uber.UberApp.DTO.RideRequeatDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.RideRequest;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.User;
import com.project.uber.UberApp.Entities.enums.RideRequestStatus;
import com.project.uber.UberApp.Services.RiderService;
import com.project.uber.UberApp.Strategies.DriverMatchStratergy;
import com.project.uber.UberApp.Strategies.RideFareCalculationStratergy;
import com.project.uber.UberApp.repositories.RideRequestRepository;
import com.project.uber.UberApp.repositories.RiderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImple implements RiderService {

    private final ModelMapper modelMapper;

    private final RideFareCalculationStratergy rideFareCalculationStratergyd;

    private final DriverMatchStratergy driverMatchStratergy;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    @Override

    public RideRequeatDTO requestRide(RideRequeatDTO rideRequestDTO) {

        RideRequest rideRequest = modelMapper.map(rideRequestDTO,RideRequest.class);
         rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

         Double fair = rideFareCalculationStratergyd.calculateFare(rideRequest);
         rideRequest.setFare(fair);

         RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
         driverMatchStratergy.matchMatchingDriver(rideRequest);
         return modelMapper.map(rideRequest,RideRequeatDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllMyRides() {
        return null;
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().
                user(user).rating(0.0).name("").build();

        return riderRepository.save(rider);
    }
}
