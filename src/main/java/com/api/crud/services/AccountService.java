package com.api.crud.services;

import com.api.crud.models.Account;
import com.api.crud.models.Customer;
import com.api.crud.repositories.AccountRepository;
import com.api.crud.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    //Method to obtain an account by ID
    public Account findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null); // Retorna null si no se encuentra la cuenta
    }

    //Method to create or update an account
    public Account save(Account account) {
        return accountRepository.save(account);
    }


    // Create a new account for a specific customer
    public Account createAccount(Long customerId, Account account) {
        // Find the customer by ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

        // Set the customer to the account
        account.setCustomer(customer);

        // Save the account
        return accountRepository.save(account);
    }

    // Get an account by ID
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));  // Lanzamos una excepción si no se encuentra
    }

    // Get all accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Update an account
    public Account updateAccount(Long id, Account updatedAccount) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                    existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
                    existingAccount.setAccountType(updatedAccount.getAccountType());
                    existingAccount.setInitialBalance(updatedAccount.getInitialBalance());
                    existingAccount.setBalance(updatedAccount.getBalance());
                    existingAccount.setStatus(updatedAccount.isStatus());
                    existingAccount.setCustomer(updatedAccount.getCustomer());  // Actualizamos el cliente si es necesario
                    return accountRepository.save(existingAccount);
                })
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
    }

    // Delete an account
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
