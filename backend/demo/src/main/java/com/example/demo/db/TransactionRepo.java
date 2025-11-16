package com.example.demo.db;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Transaction;

public class TransactionRepo implements TransactionRepoInterface {

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> pom = new ArrayList<Transaction>();
        pom.add(new Transaction("2025-10-11", "1111-2222-3333", "Mihailo P", 125, "Settled"));
        pom.add(new Transaction("2025-03-01", "1234-5678-9012", "Nadja P", 1000, "Pending"));
        return pom;
    }

    @Override
    public boolean addTransaction(String date, String name, String status, int amount, String number) {
        return true;
    }
    
}
