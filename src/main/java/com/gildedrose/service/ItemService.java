package com.gildedrose.service;

import com.gildedrose.entity.Item;

import java.util.List;

public interface ItemService {

    void updateItems();

    void save(Item item);

    void delete(Item item);

    Item findByName(String name);

    List<Item> findAll();
}
