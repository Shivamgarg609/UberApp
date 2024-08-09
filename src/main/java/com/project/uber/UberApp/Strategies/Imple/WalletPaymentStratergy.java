package com.project.uber.UberApp.Strategies.Imple;

import com.project.uber.UberApp.Entities.Driver;
import com.project.uber.UberApp.Entities.Payment;
import com.project.uber.UberApp.Entities.Rider;
import com.project.uber.UberApp.Entities.enums.PaymentStatus;
import com.project.uber.UberApp.Entities.enums.TransactionMethod;
import com.project.uber.UberApp.Services.WalleteService;
import com.project.uber.UberApp.Strategies.PaymentStratergy;
import com.project.uber.UberApp.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStratergy implements PaymentStratergy {

    private  final WalleteService walleteService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walleteService.deductMoneyFromWallet(rider.getUser(), payment.getAmount()
        ,null,payment.getRide(), TransactionMethod.RIDETYPE);

        double driverCut = payment.getAmount() *(1-PLATFORM_COMMISSION);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
