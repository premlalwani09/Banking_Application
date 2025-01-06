package com.app.BankingApplication.Service;

import com.app.BankingApplication.DTO.TransactionRequest;
import com.app.BankingApplication.Entity.Transaction;

public interface TransactionService {

    void saveTransaction(TransactionRequest transactionRequest);
}
