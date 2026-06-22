package org.example.bankingsampleapp.service;

import org.example.bankingsampleapp.dto.CreateAccountRequest;
import org.example.bankingsampleapp.dto.TransferRequest;
import org.example.bankingsampleapp.entities.Account;
import org.example.bankingsampleapp.entities.Transaction;
import org.example.bankingsampleapp.repository.AccountRepository;
import org.example.bankingsampleapp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction create(TransferRequest request) {
        if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (accountRepository.getById(request.getFromAccountId()) == null ||
                accountRepository.getById(request.getToAccountId()) == null) {
            throw new IllegalArgumentException("Account does not exist");
        }
        BigDecimal amount = request.getAmount();
        Account fromAccount = accountRepository.getById(request.getFromAccountId());
        Account toAccount = accountRepository.getById(request.getToAccountId());
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

        Transaction transaction = new Transaction(
                null,
                fromAccount.getId(),
                toAccount.getId(),
                request.getAmount()
        );

        return transactionRepository.create(transaction);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        if (accountRepository.getById(accountId) == null) {
            throw new IllegalArgumentException("Account does not exist");
        }

        return transactionRepository.findByAccountId(accountId);
    }
}
