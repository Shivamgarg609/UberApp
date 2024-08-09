package com.project.uber.UberApp.repositories;

import com.project.uber.UberApp.Entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransaction,Long> {
}
