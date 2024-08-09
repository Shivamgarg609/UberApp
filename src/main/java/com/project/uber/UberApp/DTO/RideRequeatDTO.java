package com.project.uber.UberApp.DTO;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.enums.PaymentMethods;
import com.project.uber.UberApp.Entities.enums.RideRequestStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequeatDTO {


    private  Long id;


    private PointDTO pickupLocation;


    private PointDTO dropOfLocation;


    private LocalDateTime createdTime;


    private Rider rider;


    private Driver driver;


    private PaymentMethods paymentMethod;


    private RideRequestStatus rideRequestStatus;

    private Double fare;
}
