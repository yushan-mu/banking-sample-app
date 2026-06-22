package org.example.bankingsampleapp.controller;

import org.example.bankingsampleapp.dto.CreateAccountRequest;
import org.example.bankingsampleapp.entities.Account;
import org.example.bankingsampleapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public Account create(@RequestBody CreateAccountRequest request) {
        return accountService.create(request);
    }
}
