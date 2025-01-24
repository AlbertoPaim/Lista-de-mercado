package com.albertopaim.Todolist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "itens")
@AllArgsConstructor

public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "unidade", nullable = false)
    private int unidade;

    @Enumerated(EnumType.STRING)
    private ItemType categoria;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "compra_id")
    @JsonBackReference
    private Compra compra;

    public Item() {

    }


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getUnidade() {
        return unidade;
    }

    public ItemType getCategoria() {
        return categoria;
    }

    public boolean isStatus() {
        return status;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public void setCategoria(ItemType categoria) {
        this.categoria = categoria;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
