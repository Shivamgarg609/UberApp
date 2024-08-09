package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.Entities.Ride;
import com.project.uber.UberApp.Entities.User;
import com.project.uber.UberApp.Entities.Wallet;
import com.project.uber.UberApp.Entities.enums.TransactionMethod;

public interface WalleteService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount,
                                  String transactionId, Ride ride,
                                  TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
