package com.project.uber.UberApp.Services.Implememtation;

import com.project.uber.UberApp.Entities.Payment;
import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.enums.PaymentStatus;
import com.project.uber.UberApp.Exceptions.ResourceNotFoundException;
import com.project.uber.UberApp.Services.PaymentService;
import com.project.uber.UberApp.Strategies.PaymentStratergyManager;
import com.project.uber.UberApp.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStratergyManager paymentStratergyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException(("Payment not found for ride with id" + ride.getId())));
paymentStratergyManager.paymentStratergy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder().
                ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}
