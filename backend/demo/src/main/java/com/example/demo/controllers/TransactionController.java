package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db.TransactionRepo;
import com.example.demo.models.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transactions")
//will maybe need crossorigin annotation
public class TransactionController {
    
    @GetMapping()
    public ResponseEntity<List<Transaction>> getAll(){
        List<Transaction> transactions = new TransactionRepo().getAllTransactions();
        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(transactions);
    }

    @PostMapping()
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        String message = new TransactionRepo().addTransaction(transaction);
        if("Success".equals(message)){
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        
    }
    

}
