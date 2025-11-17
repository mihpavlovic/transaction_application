package com.example.demo.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.example.demo.models.Transaction;

public class TransactionRepo implements TransactionRepoInterface {

    private static final String FILE_NAME = "data/transactions.csv";

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new java.io.FileInputStream(FILE_NAME)))) {

            String line;

            br.readLine(); //skip line with names

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Transaction t = new Transaction(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]);
                list.add(t);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV", e);
        }

        return list;
    }

    

    private String validateTransaction(Transaction transaction) {
        final List<String> VALID_STATUSES = Arrays.asList("Settled", "Pending", "Failed");
        final Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("\\d{4}-\\d{4}-\\d{4}");
        final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

        if (transaction.getTransactionDate() == null || transaction.getTransactionDate().isEmpty())
            return "Transaction Date is required";
        if (transaction.getAccountNumber() == null || transaction.getAccountNumber().isEmpty())
            return "Account Number is required";
        if (transaction.getAccountHolderName() == null || transaction.getAccountHolderName().isEmpty())
            return "Account Holder Name is required";
        if (transaction.getStatus() == null || transaction.getStatus().isEmpty())
            return "Status is required";

        try {
            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(transaction.getTransactionDate());
        } catch (ParseException e) {
            return "Transaction Date invalid";
        }
        if (!ACCOUNT_NUMBER_PATTERN.matcher(transaction.getAccountNumber()).matches()) {
            return "Account Number invalid.";
        }
        if (transaction.getAmount() < 0) {
            return "Amount invalid";
        }
        if (!VALID_STATUSES.contains(transaction.getStatus())) {
            return "Status invalid";
        }

        return "OK";
    }

    @Override
    public String addTransaction(Transaction transaction) {
        
        String validationMessage = validateTransaction(transaction);
        if(!"OK".equals(validationMessage)){
            return validationMessage;
        }
        
        try (FileWriter fw = new FileWriter(FILE_NAME, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            String line = String.join(",",
                transaction.getTransactionDate(),
                transaction.getAccountNumber(),
                transaction.getAccountHolderName(),
                String.valueOf(transaction.getAmount()),
                transaction.getStatus()
            );
            out.println(line);
            return "Success";
    
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    
}
