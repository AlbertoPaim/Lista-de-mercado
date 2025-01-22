package com.albertopaim.Todolist.controllers;

import com.albertopaim.Todolist.Dtos.CompraDTO;
import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.repositories.CompraRepository;
import com.albertopaim.Todolist.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping()
    public ResponseEntity<List<CompraDTO>> getCompra() {

        List<Compra> resultado = compraService.listarCompras();

        List<CompraDTO> compras = new ArrayList<>();

        for (Compra compra : resultado) {
            CompraDTO compraDTO = new CompraDTO(compra.getNome(), compra.getDataCompra());

            compras.add(compraDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(compras);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompraDTO> getCompraId(@PathVariable String id) throws Exception {

        var uuid = UUID.fromString(id);
        Optional<Compra> compraEncontrada = compraRepository.findCompraById(uuid);

        if (compraEncontrada.isEmpty()) {
            throw new Exception("Compra não encontrada");
        } else {
            Compra compra = compraEncontrada.get();
            CompraDTO compraDTO = new CompraDTO(compra.getNome(), compra.getDataCompra());
            return ResponseEntity.status(HttpStatus.OK).body(compraDTO);
        }

    }


    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody CompraDTO compraDTO) throws Exception {

        var uuid = UUID.fromString(id);

        Optional<Compra> compraEncontrada = compraRepository.findCompraById(uuid);

        if (compraEncontrada.isPresent()) {

            Compra compra = compraEncontrada.get();
            compra.setNome(compraDTO.nome());
            compra.setDataCompra(compraDTO.dataCompra());

            compraService.updateCompra(compra);

            return ResponseEntity.status(HttpStatus.OK).body(compraEncontrada);

        } else {
            throw new Exception("Compra não encontrada");
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable String id) throws Exception {
        var uuid = UUID.fromString(id);
        compraService.deletar(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
