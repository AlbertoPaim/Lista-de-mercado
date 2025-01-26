package com.albertopaim.MarketList.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table(name = "compra")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", unique = true)
    private String nome;

    @CreatedDate
    @Column(name = "data_compra")
    private LocalDate dataCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Item> itens;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
