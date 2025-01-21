package com.albertopaim.Todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table(name = "compra")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Item> itens;
}
