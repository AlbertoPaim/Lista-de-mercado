package com.albertopaim.MarketList.services;

import com.albertopaim.MarketList.Exceptions.UnprocessableEntity;
import com.albertopaim.MarketList.model.Compra;
import com.albertopaim.MarketList.model.Item;
import com.albertopaim.MarketList.repositories.CompraRepository;
import com.albertopaim.MarketList.repositories.ItemRepository;
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

        Optional<Item> itemMesmoNome = itemRepository.findByNome(item.getNome());

        if(itemMesmoNome.isPresent()){

            Item itemAtual = itemMesmoNome.get();

            itemAtual.setUnidade(itemAtual.getUnidade() + item.getUnidade() );

            return  itemRepository.save(itemAtual);
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

    public void updateItem(UUID uuid, Item itemBody) {
        Optional<Item> itemEncontrado = itemRepository.findById(uuid);

        if (itemEncontrado.isEmpty()) {
            throw new UnprocessableEntity("Item não encontrado");
        }

        Item item = itemEncontrado.get();
        item.setNome(itemBody.getNome());
        item.setUnidade(itemBody.getUnidade());
        item.setStatus(itemBody.isStatus());
        item.setCategoria(itemBody.getCategoria());

        itemRepository.save(item);

    }

}
