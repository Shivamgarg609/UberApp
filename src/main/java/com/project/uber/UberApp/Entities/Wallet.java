package com.project.uber.UberApp.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
//@Getter
//@Setter
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<WalletTransaction> transactions;


}
