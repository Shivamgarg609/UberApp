package com.project.uber.UberApp.Services;

import com.project.uber.UberApp.DTO.WalletTransactionDTO;
import com.project.uber.UberApp.Entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
