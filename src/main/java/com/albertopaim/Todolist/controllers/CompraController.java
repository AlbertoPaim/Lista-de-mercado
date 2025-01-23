package com.albertopaim.Todolist.controllers;

import com.albertopaim.Todolist.Dtos.CompraDTO;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Compra>> getCompra(){

        List listaDto = resultado.stream().map(autor -> new AutorDTO(autor.getId(), autor.getName(), autor.getDataNascimento(), autor.getNacionalidade())).collect(Collectors.toList())
        List<Compra> resultado = compraService.listarCompras();
        return resultado;
    }

}
