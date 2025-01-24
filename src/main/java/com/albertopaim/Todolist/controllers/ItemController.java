package com.albertopaim.Todolist.controllers;

import com.albertopaim.Todolist.Dtos.ItemDTO;
import com.albertopaim.Todolist.Exceptions.UnprocessableEntity;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.model.Item;
import com.albertopaim.Todolist.repositories.CompraRepository;
import com.albertopaim.Todolist.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemService itemService;

    @PostMapping("{id}")
    public ResponseEntity<ItemDTO> createItem(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        var uuid = UUID.fromString(id);

        Item newItem = new Item();
        newItem.setNome(itemDTO.nome());
        newItem.setCategoria(itemDTO.categoria());
        newItem.setStatus(itemDTO.status());
        newItem.setUnidade(itemDTO.unidade());

        itemService.createItem(newItem, uuid);

        ItemDTO newItemDTO = new ItemDTO(newItem.getNome(), newItem.getUnidade(), newItem.getCategoria(), newItem.isStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(newItemDTO);
    }
}
