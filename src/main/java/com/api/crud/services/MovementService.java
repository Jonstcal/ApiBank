package com.api.crud.services;

import com.api.crud.models.Account;
import com.api.crud.models.Movement;
import com.api.crud.repositories.AccountRepository;
import com.api.crud.repositories.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    public MovementService(MovementRepository movementRepository, AccountRepository accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
    }

    // Create a new movement
    public Movement createMovement(Movement movement) {
        // Retrieve the associated account
        Account account = accountRepository.findById(movement.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + movement.getAccount().getId()));

        // Perform validations for debit type movement (insufficient balance check)
        if (movement.getType().equalsIgnoreCase("debit") && account.getBalance() < movement.getValue()) {
            throw new RuntimeException("Insufficient balance for this transaction.");
        }

        // Update account balance based on movement type
        if (movement.getType().equalsIgnoreCase("credit")) {
            account.setBalance(account.getBalance() + movement.getValue());
        } else if (movement.getType().equalsIgnoreCase("debit")) {
            account.setBalance(account.getBalance() - movement.getValue());
        } else {
            throw new IllegalArgumentException("Invalid movement type: " + movement.getType());
        }

        // Save the updated account balance
        accountRepository.save(account);

        // Set the updated balance in the movement object
        movement.setBalance(account.getBalance());

        // Save and return the movement
        return movementRepository.save(movement);
    }

    // Get all movements
    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    // Get movements by account ID
    public List<Movement> getMovementsByAccountId(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
        return movementRepository.findByAccountId(accountId);
    }

    // Delete a movement by ID
    public void deleteMovement(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movement not found with ID: " + id));

        // Retrieve the associated account to update its balance
        Account account = accountRepository.findById(movement.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + movement.getAccount().getId()));

        // Reverse the movement (credit becomes debit and vice versa)
        if (movement.getType().equalsIgnoreCase("credit")) {
            account.setBalance(account.getBalance() - movement.getValue());
        } else if (movement.getType().equalsIgnoreCase("debit")) {
            account.setBalance(account.getBalance() + movement.getValue());
        } else {
            throw new IllegalArgumentException("Invalid movement type: " + movement.getType());
        }

        // Save updated balance in the account
        accountRepository.save(account);

        // Delete the movement
        movementRepository.delete(movement);
    }
}
