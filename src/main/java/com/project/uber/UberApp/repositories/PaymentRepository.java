package com.project.uber.UberApp.repositories;

import com.project.uber.UberApp.Entities.Payment;
import com.project.uber.UberApp.Entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByRide(Ride ride);
}
