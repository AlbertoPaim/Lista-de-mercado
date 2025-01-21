package com.albertopaim.Todolist.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RequestMapping("/itens")
@RestController
public class Item {
    @PostMapping
    public void createItem() {

    }
}
