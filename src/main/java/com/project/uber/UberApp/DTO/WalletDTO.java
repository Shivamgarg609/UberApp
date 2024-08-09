package com.project.uber.UberApp.DTO;

import com.project.uber.UberApp.Entities.User;
import com.project.uber.UberApp.Entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class WalletDTO {

    private Long id;

    private Double balance;


    private User user;

    private List<WalletTransaction> transactions;
}
