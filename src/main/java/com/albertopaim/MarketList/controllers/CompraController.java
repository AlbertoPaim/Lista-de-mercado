package com.albertopaim.MarketList.controllers;

import com.albertopaim.MarketList.Dtos.CompraDTO;
import com.albertopaim.MarketList.model.Compra;
import com.albertopaim.MarketList.repositories.CompraRepository;
import com.albertopaim.MarketList.services.CompraService;
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
    public ResponseEntity<HttpStatus> createCompra(@RequestBody CompraDTO compraDTO) {
        compraService.create(compraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> getCompras() {

        List<Compra> resultado = compraService.listarCompras();
        List<CompraDTO> comprasDTO = resultado.stream().map(compra -> new CompraDTO(compra.getNome(), compra.getDataCompra(), null)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(comprasDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompraDTO> getCompra(@PathVariable String id) {
        var uuid = UUID.fromString(id);
        Optional<Compra> compraEncontrada = compraRepository.findCompraById(uuid);

        if (compraEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            Compra compra = compraEncontrada.get();

            CompraDTO compraDTO = new CompraDTO(compra.getNome(), compra.getDataCompra(), compra.getItens());

            return ResponseEntity.status(HttpStatus.OK).body(compraDTO);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCompra(@PathVariable String id) throws Exception {
        var uuid = UUID.fromString(id);
        compraService.deletar(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCompra(@PathVariable String id, @RequestBody CompraDTO compraDTO) {
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
