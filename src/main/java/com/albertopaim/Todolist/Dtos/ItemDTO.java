package com.albertopaim.Todolist.Dtos;

import com.albertopaim.Todolist.model.Compra;
import com.albertopaim.Todolist.model.ItemType;

public record ItemDTO(

        String nome,
        Integer unidade,
        ItemType categoria,
        Boolean status

) {

}
