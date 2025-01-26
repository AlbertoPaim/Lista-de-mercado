package com.albertopaim.MarketList.Dtos;
import com.albertopaim.MarketList.model.ItemType;

public record ItemDTO(

        String nome,
        Integer unidade,
        ItemType categoria,
        Boolean status

) {

}
