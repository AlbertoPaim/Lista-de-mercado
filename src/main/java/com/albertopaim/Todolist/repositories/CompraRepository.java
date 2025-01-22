package com.albertopaim.Todolist.repositories;

import com.albertopaim.Todolist.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompraRepository extends JpaRepository<Compra, UUID> {
    Optional<Compra> findCompraById (UUID id);
}
