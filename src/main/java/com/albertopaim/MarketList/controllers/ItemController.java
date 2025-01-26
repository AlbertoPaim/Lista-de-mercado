package com.albertopaim.MarketList.controllers;

import com.albertopaim.MarketList.Dtos.ItemDTO;
import com.albertopaim.MarketList.model.Item;
import com.albertopaim.MarketList.repositories.CompraRepository;
import com.albertopaim.MarketList.repositories.ItemRepository;
import com.albertopaim.MarketList.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/item")
@RestController
public class ItemController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("{id}")
    public ResponseEntity<HttpStatus> createItem(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        var uuid = UUID.fromString(id);

        Item newItem = new Item();
        newItem.setNome(itemDTO.nome());
        newItem.setCategoria(itemDTO.categoria());
        newItem.setStatus(itemDTO.status());
        newItem.setUnidade(itemDTO.unidade());

        itemService.createItem(newItem, uuid);

        ItemDTO newItemDTO = new ItemDTO(newItem.getNome(), newItem.getUnidade(), newItem.getCategoria(), newItem.isStatus());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) throws Exception {

        var uuid = UUID.fromString(id);

        itemService.deleteItem(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> listarItens() {

        List<Item> items = itemService.getItens();

        List<ItemDTO> listDto = items.stream().map(item -> new ItemDTO(item.getNome(), item.getUnidade(), item.getCategoria(), item.isStatus())).toList();

        return ResponseEntity.status(HttpStatus.OK).body(listDto);

    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> listarItem(@PathVariable String id) {
        var uuid = UUID.fromString(id);

        Item item = itemService.getItem(uuid);

        ItemDTO itemDTO = new ItemDTO(item.getNome(), item.getUnidade(), item.getCategoria(), item.isStatus());

        return ResponseEntity.status(HttpStatus.OK).body(itemDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateItem(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        var uuid = UUID.fromString(id);

        Item item = new Item();

        item.setNome(itemDTO.nome());
        item.setUnidade(itemDTO.unidade());
        item.setCategoria(itemDTO.categoria());
        item.setStatus(itemDTO.status());

        itemService.updateItem(uuid, item);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
