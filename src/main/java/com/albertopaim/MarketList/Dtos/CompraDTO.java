package com.albertopaim.MarketList.Dtos;

import com.albertopaim.MarketList.model.Item;

import java.time.LocalDate;
import java.util.List;

public record CompraDTO(String nome, LocalDate dataCompra, List<Item> items) {
}
