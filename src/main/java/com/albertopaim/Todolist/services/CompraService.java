package com.albertopaim.Todolist.services;

import com.albertopaim.Todolist.Dtos.CompraDTO;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

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


}
