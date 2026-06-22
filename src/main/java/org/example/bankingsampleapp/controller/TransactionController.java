package org.example.bankingsampleapp.controller;

import org.example.bankingsampleapp.dto.TransferRequest;
import org.example.bankingsampleapp.entities.Transaction;
import org.example.bankingsampleapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public Transaction create(@RequestBody TransferRequest request) {
        return transactionService.create(request);
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
