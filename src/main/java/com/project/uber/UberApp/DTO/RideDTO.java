package com.project.uber.UberApp.DTO;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.enums.PaymentMethods;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {

    private  Long id;


    private Point pickupLocation;


    private Point dropOfLocation;


    private LocalDateTime createdTime;


    private Rider rider;


    private DriverDTO driver;

    private String OTP;
    private PaymentMethods paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private boolean fare;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
