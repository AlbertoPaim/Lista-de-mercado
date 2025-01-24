package com.albertopaim.Todolist.services;

import com.albertopaim.Todolist.Exceptions.UnprocessableEntity;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.model.Item;
import com.albertopaim.Todolist.repositories.CompraRepository;
import com.albertopaim.Todolist.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(Item item, UUID id_compra) {

        Optional<Compra> compraEncontrada = compraRepository.findCompraById(id_compra);

        if (compraEncontrada.isEmpty()) {
            throw new UnprocessableEntity("Compra não encontrada");
        }

        Compra compra = compraEncontrada.get();

        item.setCompra(compra);

        return itemRepository.save(item);
    }

    public void deleteItem(UUID id) throws Exception {
        Optional<Item> itemEncontrado = itemRepository.findById(id);

        if (itemEncontrado.isEmpty()) {
            throw new UnprocessableEntity("Item não foi encontrado");
        }

        itemRepository.deleteById(id);
    }

    public List<Item> getItens() {
        return itemRepository.findAll();
    }

    public Item getItem(UUID uuid) {
        Optional<Item> itemEncontrado = itemRepository.findById(uuid);

        if (itemEncontrado.isEmpty()) {
            throw new UnprocessableEntity("Item não encontrado");
        }

        Item item = itemEncontrado.get();

        return item;
    }

    public Item updateItem(UUID uuid, Item itemBody) {
        Optional<Item> itemEncontrado = itemRepository.findById(uuid);

        if (itemEncontrado.isEmpty()) {
            throw new UnprocessableEntity("Item não encontrado");
        }

        Item item = itemEncontrado.get();
        item.setNome(itemBody.getNome());
        item.setUnidade(itemBody.getUnidade());
        item.setStatus(itemBody.isStatus());
        item.setCategoria(itemBody.getCategoria());

        return itemRepository.save(item);

    }

}
