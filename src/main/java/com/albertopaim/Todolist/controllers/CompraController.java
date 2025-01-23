package com.albertopaim.Todolist.controllers;

import com.albertopaim.Todolist.Dtos.CompraDTO;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.repositories.CompraRepository;
import com.albertopaim.Todolist.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    public ResponseEntity<CompraController> createCompra(@RequestBody CompraDTO compraDTO) {
        compraService.create(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> getCompras() {

        List<Compra> resultado = compraService.listarCompras();
        List<CompraDTO> comprasDTO = resultado.stream().map(compra -> new CompraDTO(compra.getNome(), compra.getDataCompra())).toList();

        return ResponseEntity.status(HttpStatus.OK).body(comprasDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompraDTO> getCompra(@PathVariable String id) throws Exception {
        var uuid = UUID.fromString(id);
        Optional<Compra> compraEncontrada = compraRepository.findCompraById(uuid);

        if (compraEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            Compra compra = compraEncontrada.get();

            CompraDTO compraDTO = new CompraDTO(compra.getNome(), compra.getDataCompra());

            return ResponseEntity.status(HttpStatus.OK).body(compraDTO);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCompra(@PathVariable String id) throws Exception {
        var uuid = UUID.fromString(id);
        compraService.deletar(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateCompra(@PathVariable String id, @RequestBody CompraDTO compraDTO) {
        var uuid = UUID.fromString(id);

        Optional<Compra> compraEncontrada = compraRepository.findCompraById(uuid);

        if (compraEncontrada.isPresent()) {

            Compra compra = compraEncontrada.get();
            compra.setNome(compraDTO.nome());
            compra.setDataCompra(compraDTO.dataCompra());

            compraService.updateCompra(compra);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
