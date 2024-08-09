package com.project.uber.UberApp.DTO;

import com.project.uber.UberApp.Entities.enums.TransactionMethod;
import com.project.uber.UberApp.Entities.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDTO {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDTO ride;

    private String transactionId;

    private WalletDTO wallet;

    private LocalDateTime timeStamp;
}
