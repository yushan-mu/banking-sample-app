package org.example.bankingsampleapp.repository;

import org.example.bankingsampleapp.dto.CreateAccountRequest;
import org.example.bankingsampleapp.entities.Account;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepository {
    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Account create(CreateAccountRequest request) {
        Long id = nextId.getAndIncrement();
        Account account = new Account(id, request.getBalance());
        accounts.put(id, account);
        return account;
    }

    public Account getById(Long id) {
        return accounts.get(id);
    }

    public void update(Account account) {
        accounts.put(account.getId(), account);
    }
}
