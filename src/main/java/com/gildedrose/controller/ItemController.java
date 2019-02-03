package com.gildedrose.controller;

import com.gildedrose.entity.Item;
import com.gildedrose.service.ItemService;
import com.gildedrose.service.ItemServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ItemController {

    private final ItemServiceImpl itemServiceImpl;

    public ItemController(ItemServiceImpl itemServiceImpl){
        this.itemServiceImpl = itemServiceImpl;
    }

    public List<Item> getAllItems(){
        return itemServiceImpl.findAll();
    }

    @RequestMapping(value = "/getAllItemsList/", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getAllItemsList(){
        List<Item> items = itemServiceImpl.findAll();

        if(items.size()>0) {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
