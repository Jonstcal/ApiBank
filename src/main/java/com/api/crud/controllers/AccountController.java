package com.api.crud.controllers;

import com.api.crud.models.Account;
import com.api.crud.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    //AccountService service dependency injection
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Create account associated with a client
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestParam Long customerId, @RequestBody Account account) {

        Account createdAccount = accountService.createAccount(customerId, account);
        return ResponseEntity.ok(createdAccount);
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra la cuenta, retorna 404
        }
        return ResponseEntity.ok(account);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account account = accountService.findAccountById(id); // Encuentra la cuenta por ID

        if (account == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra la cuenta, retorna 404
        }


        account.setAccountNumber(accountDetails.getAccountNumber());
        account.setAccountType(accountDetails.getAccountType());
        account.setInitialBalance(accountDetails.getInitialBalance());
        account.setBalance(accountDetails.getBalance());
        account.setStatus(accountDetails.isStatus());


        if (accountDetails.getCustomer() != null) {
            account.setCustomer(accountDetails.getCustomer());
        }


        accountService.save(account);

        return ResponseEntity.ok(account);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
