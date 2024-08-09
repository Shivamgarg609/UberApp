package com.project.uber.UberApp.repositories;

import com.project.uber.UberApp.Entities.User;
import com.project.uber.UberApp.Entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(User user);
}
