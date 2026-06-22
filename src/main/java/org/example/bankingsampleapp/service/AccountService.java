package org.example.bankingsampleapp.service;

import org.example.bankingsampleapp.dto.CreateAccountRequest;
import org.example.bankingsampleapp.entities.Account;
import org.example.bankingsampleapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(CreateAccountRequest request) {
        if (request.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        return accountRepository.create(request);
    }

    public Account getById(Long id) {
        if (accountRepository.getById(id) == null) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return accountRepository.getById(id);
    }
}
