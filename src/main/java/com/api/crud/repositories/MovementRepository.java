package com.api.crud.repositories;

import com.api.crud.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    // Custom query to find movements by account ID
    List<Movement> findByAccountId(Long accountId);
}
