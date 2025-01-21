package com.albertopaim.Todolist.Dtos;

import java.util.UUID;

public record ItemDTO(
        String nome,
        Integer unidade,
        String categoria,
        Boolean status
) {


}
