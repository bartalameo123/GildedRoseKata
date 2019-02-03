package com.gildedrose.service;

import com.gildedrose.entity.Item;
import com.gildedrose.repository.ItemRepository;
import com.gildedrose.usecase.Quality;
import com.gildedrose.usecase.SellTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    void updateItems(List<Item> items);

    void save(Item item);

    void delete(Item item);

    Item findByName(String name);

    List<Item> findAll();
}
