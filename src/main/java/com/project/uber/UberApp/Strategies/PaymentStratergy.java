package com.project.uber.UberApp.Strategies;

import com.project.uber.UberApp.Entities.Payment;

public interface PaymentStratergy {

    final Double PLATFORM_COMMISSION  = 0.3;

    void processPayment(Payment payment);

 //   deductMoneyFromWallet()
}
