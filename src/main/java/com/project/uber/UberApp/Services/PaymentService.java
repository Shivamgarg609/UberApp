package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.Entities.Payment;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
