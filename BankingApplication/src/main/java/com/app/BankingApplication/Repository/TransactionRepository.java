package com.app.BankingApplication.Repository;

import com.app.BankingApplication.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
