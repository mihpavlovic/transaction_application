package com.example.demo.db;

import java.util.List;

import com.example.demo.models.Transaction;

public interface TransactionRepoInterface {
    
    public List<Transaction> getAllTransactions();

    public boolean addTransaction(String date, String name, String status, int amount, String number);
    
}
