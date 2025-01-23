package com.albertopaim.Todolist.controllers;

import com.albertopaim.Todolist.Dtos.CompraDTO;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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
    public ResponseEntity<List<CompraDTO>> getCompra(){

        List<Compra> resultado = compraService.listarCompras();
        List<CompraDTO> comprasDTO = resultado.stream().map( compra -> new CompraDTO(compra.getNome(), compra.getDataCompra())).toList();

        return ResponseEntity.status(HttpStatus.OK).body(comprasDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCompra(@PathVariable String id) throws Exception {
         var uuid = UUID.fromString(id);
         compraService.deletar(uuid);

         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
