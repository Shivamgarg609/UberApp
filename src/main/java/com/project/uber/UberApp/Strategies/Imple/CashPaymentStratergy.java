package com.project.uber.UberApp.Strategies.Imple;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Payment;
import com.project.uber.UberApp.Entities.Wallet;
import com.project.uber.UberApp.Entities.enums.PaymentStatus;
import com.project.uber.UberApp.Entities.enums.TransactionMethod;
import com.project.uber.UberApp.Entities.enums.TransactionType;
import com.project.uber.UberApp.Services.PaymentService;
import com.project.uber.UberApp.Services.WalleteService;
import com.project.uber.UberApp.Strategies.PaymentStratergy;
import com.project.uber.UberApp.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider ->100
//Driver -> 70 and deduct 30rs from Driver's wallet
@Service
@RequiredArgsConstructor

public class CashPaymentStratergy implements PaymentStratergy {

   private final WalleteService walleteService;
   private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
     Wallet driverWallet = walleteService.findByUser(driver.getUser());
     double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;

        walleteService.deductMoneyFromWallet(driver.getUser(), platformCommission, null,
                payment.getRide(), TransactionMethod.RIDETYPE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
