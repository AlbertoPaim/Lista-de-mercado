package com.albertopaim.Todolist.Dtos;

public record ItemDTO(
        String nome,
        Integer unidade,
        String categoria,
        Boolean status
) {

}
