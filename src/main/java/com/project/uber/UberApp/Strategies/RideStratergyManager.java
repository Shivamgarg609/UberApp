package com.project.uber.UberApp.Strategies;


import com.project.uber.UberApp.Strategies.Imple.DriverMatchHighestRatedDriverStratergy;
import com.project.uber.UberApp.Strategies.Imple.DriverMatchingNearestDriverStratergy;
import com.project.uber.UberApp.Strategies.Imple.RideFareDefaultFareCalculationStr;
import com.project.uber.UberApp.Strategies.Imple.RideFareSurgePricingFareCalculationStrat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStratergyManager {

   private final DriverMatchingNearestDriverStratergy driverMatchingNearestDriverStratergy;
   private final DriverMatchHighestRatedDriverStratergy driverMatchHighestRatedDriverStratergy;
   private final RideFareSurgePricingFareCalculationStrat rideFareSurgePricingFareCalculationStrat;
   private final RideFareDefaultFareCalculationStr rideFareDefaultFareCalculationStr;


    public DriverMatchStratergy driverMatchingStrategy(double riderRating) {
        if(riderRating >= 4.8) {
            return driverMatchHighestRatedDriverStratergy;
        } else {
            return driverMatchingNearestDriverStratergy;
        }
    }

    public RideFareCalculationStratergy rideFareCalculationStrategy() {

//        6PM to 9PM is SURGE TIME
        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime) {
            return rideFareSurgePricingFareCalculationStrat;
        } else {
            return rideFareDefaultFareCalculationStr;
        }
    }
}
