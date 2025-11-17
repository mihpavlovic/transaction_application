package com.example.demo.db;

import java.util.List;

import com.example.demo.models.Transaction;

public interface TransactionRepoInterface {
    
    public List<Transaction> getAllTransactions();

    public String addTransaction(Transaction transaction);
    
}
