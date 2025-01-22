package com.albertopaim.Todolist.controllers;

import com.albertopaim.Todolist.Dtos.CompraDTO;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraController> createCompra(@RequestBody CompraDTO compraDTO) {
        compraService.create(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<CompraDTO> getCompra() {

        List<Compra> resultado = compraService.listarCompras();

        List<CompraDTO> compras = new ArrayList<>();

        for (Compra compra : resultado) {
            CompraDTO compraDTO = new CompraDTO(compra.getNome(), compra.getDataCompra());

            compras.add(compraDTO);
        }
        return compras;
    }
}
