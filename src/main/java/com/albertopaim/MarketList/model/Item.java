package com.albertopaim.MarketList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "itens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
