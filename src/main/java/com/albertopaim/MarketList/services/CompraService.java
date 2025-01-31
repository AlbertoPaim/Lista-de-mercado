package com.albertopaim.MarketList.services;

import com.albertopaim.MarketList.Dtos.CompraDTO;
import com.albertopaim.MarketList.Exceptions.UnprocessableEntity;
import com.albertopaim.MarketList.model.Compra;
import com.albertopaim.MarketList.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public Compra create(CompraDTO compraDto) {
        Compra compra = new Compra();
        compra.setNome(compraDto.nome());
        compra.setDataCompra(compraDto.dataCompra());
        return compraRepository.save(compra);
    }

    public List<Compra> listarCompras() {
        List<Compra> compras = compraRepository.findAll();
        return compras;
    }

    public void deletar(UUID id) throws Exception {
        Optional<Compra> compraAchada = compraRepository.findCompraById(id);
        if (compraAchada.isPresent()) {
            compraRepository.deleteById(id);
        } else {
            throw new UnprocessableEntity("Compra não encontrada");
        }
    }

    public Compra updateCompra(Compra compra) {
        return compraRepository.save(compra);
    }
}
