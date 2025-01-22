package com.albertopaim.Todolist.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/itens")
@RestController
public class ItemController {
    @PostMapping
    public void createItem() {

    }
}
