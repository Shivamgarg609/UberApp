package com.project.uber.UberApp.DTO;

import com.project.uber.UberApp.Entities.enums.PaymentMethods;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RideDTO {

    private  Long id;


    private PointDTO pickupLocation;


    private PointDTO dropOfLocation;


    private LocalDateTime createdTime;


    private RiderDTO riderDTO;


    private DriverDTO driver;

    private String OTP;
    private PaymentMethods paymentMethod;

    private RideStatus rideStatus;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
