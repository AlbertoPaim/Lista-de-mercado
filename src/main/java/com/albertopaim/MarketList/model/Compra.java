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
@Setter
@NoArgsConstructor
@Getter
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

}
