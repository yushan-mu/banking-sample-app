package org.example.bankingsampleapp.dto;

import java.math.BigDecimal;

public class CreateAccountRequest {
    private BigDecimal balance;

    public CreateAccountRequest(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
