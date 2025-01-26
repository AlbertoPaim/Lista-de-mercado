package com.albertopaim.MarketList.repositories;

import com.albertopaim.MarketList.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    Optional<Item> findByNome(String nome);
}
