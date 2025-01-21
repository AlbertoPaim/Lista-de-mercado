package com.albertopaim.Todolist.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "itens")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
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
    private Compra compra;
}
