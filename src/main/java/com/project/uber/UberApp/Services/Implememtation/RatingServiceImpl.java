package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.DTO.DriverDTO;
import com.project.uber.UberApp.DTO.RiderDTO;
import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Rating;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Services.RatingService;
import com.project.uber.UberApp.repositories.DriverRepository;
import com.project.uber.UberApp.repositories.RatingRepository;
import com.project.uber.UberApp.repositories.RiderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;
    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Ride nor found"));
        if(ratingObj.getRiderRating()!=null){
            throw new RuntimeException("Rider is already rated");
        }
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating=   ratingRepository.findByRider(rider)
                .stream().mapToDouble(Rating::getRiderRating).average().orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);

        return modelMapper.map(savedRider, RiderDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Ride nor found"));
        if(ratingObj.getDriverRating()!=null){
            throw new RuntimeException("Driver is already rated");
        }
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);
     Double newRating=   ratingRepository.findByDriver(driver)
                            .stream().mapToDouble(Rating::getDriverRating).average().orElse(0.0);
        driver.setRating(newRating);
        Driver savedDriver = driverRepository.save(driver);

        return modelMapper.map(savedDriver, DriverDTO.class);

    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();

        ratingRepository.save(rating);
    }
}
