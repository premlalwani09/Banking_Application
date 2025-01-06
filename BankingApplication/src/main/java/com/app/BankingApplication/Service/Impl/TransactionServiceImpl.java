package com.app.BankingApplication.Service.Impl;

import com.app.BankingApplication.DTO.TransactionRequest;
import com.app.BankingApplication.Entity.Transaction;
import com.app.BankingApplication.Repository.TransactionRepository;
import com.app.BankingApplication.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {

        Transaction transaction = Transaction.builder()
                .transactionType(transactionRequest.getTransactionType())
                .accountNumber(transactionRequest.getAccountNumber())
                .amount(transactionRequest.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);
        System.out.println("Transaction Saved Successfully!");
    }
}
