package com.gildedrose.controller;

import com.gildedrose.service.ItemService;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
}
