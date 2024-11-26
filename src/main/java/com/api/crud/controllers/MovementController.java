package com.api.crud.controllers;

import com.api.crud.models.Movement;
import com.api.crud.services.MovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }


    @PostMapping
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) {
        try {
            Movement createdMovement = movementService.createMovement(movement);
            return ResponseEntity.ok(createdMovement);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        List<Movement> movements = movementService.getAllMovements();
        return ResponseEntity.ok(movements);
    }


    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Movement>> getMovementsByAccountId(@PathVariable Long accountId) {
        List<Movement> movements = movementService.getMovementsByAccountId(accountId);
        return ResponseEntity.ok(movements);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        movementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }
}
