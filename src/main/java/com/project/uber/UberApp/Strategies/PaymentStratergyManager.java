package com.project.uber.UberApp.Strategies;


import com.project.uber.UberApp.Entities.enums.PaymentMethods;
import com.project.uber.UberApp.Strategies.Imple.CashPaymentStratergy;
import com.project.uber.UberApp.Strategies.Imple.WalletPaymentStratergy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStratergyManager {

    private final WalletPaymentStratergy walletPaymentStratergy;

    private final CashPaymentStratergy cashPaymentStratergy;

    public PaymentStratergy paymentStratergy(PaymentMethods paymentMethods){
        return switch (paymentMethods) {

            case WALLET -> walletPaymentStratergy;
            case CASH -> cashPaymentStratergy;
        };
        }
    }

