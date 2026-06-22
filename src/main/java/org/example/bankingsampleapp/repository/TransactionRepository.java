package org.example.bankingsampleapp.repository;

import org.example.bankingsampleapp.entities.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransactionRepository {
    private final ConcurrentHashMap<Long, Transaction> transactions = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Transaction create(Transaction transaction) {
        Long id = nextId.getAndIncrement();

        Transaction newTransaction = new Transaction(
                id,
                transaction.getFromAccountId(),
                transaction.getToAccountId(),
                transaction.getAmount()
        );

        transactions.put(id, newTransaction);
        return newTransaction;
    }

    public List<Transaction> findByAccountId(Long accountId) {
        return transactions.values()
                .stream()
                .filter(transaction ->
                        transaction.getFromAccountId().equals(accountId) ||
                        transaction.getToAccountId().equals(accountId))
                .toList();
    }
}
