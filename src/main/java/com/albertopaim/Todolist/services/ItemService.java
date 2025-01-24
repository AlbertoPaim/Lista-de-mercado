package com.albertopaim.Todolist.services;

import com.albertopaim.Todolist.Exceptions.UnprocessableEntity;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.model.Item;
import com.albertopaim.Todolist.repositories.CompraRepository;
import com.albertopaim.Todolist.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem (Item item, UUID id_compra) {

        Optional<Compra> compraEncontrada = compraRepository.findCompraById(id_compra);

        if(compraEncontrada.isEmpty()){
            throw new UnprocessableEntity("Compra não encontrada");
        }

       Compra compra = compraEncontrada.get();

       item.setCompra(compra);

       return itemRepository.save(item);
    }
}
