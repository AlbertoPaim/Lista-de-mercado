package com.albertopaim.Todolist.Dtos;

import com.albertopaim.Todolist.model.Item;

import java.time.LocalDate;
import java.util.List;

public record CompraDTO(String nome, LocalDate dataCompra, List<Item> items) {
}
