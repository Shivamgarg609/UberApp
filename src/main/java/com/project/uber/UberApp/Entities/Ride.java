package com.project.uber.UberApp.Entities;

import com.project.uber.UberApp.Entities.enums.PaymentMethods;
import com.project.uber.UberApp.Entities.enums.RideRequestStatus;
import com.project.uber.UberApp.Entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
//@Getter
//@Setter
public class Ride implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "Geometry(Point , 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point , 4326)")
    private Point dropOfLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private Double fare;

    private String OTP;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
