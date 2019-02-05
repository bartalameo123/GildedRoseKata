package com.gildedrose.service;

import com.gildedrose.GildedRose;
import com.gildedrose.entity.Item;
import com.gildedrose.repository.ItemRepository;
import com.gildedrose.service.exceptions.ItemNotFoundException;
import com.gildedrose.usecase.Quality;
import com.gildedrose.usecase.SellTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gildedrose.service.exceptions.ItemNotFoundException.ITEM_NOT_FOUND;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    public ItemServiceImpl(
            ItemRepository itemRepository
    ){
        this.itemRepository = itemRepository;
    }

    public void updateItems() {
        List<Item> items = itemRepository.findAll();
        GildedRose app = new GildedRose(items.toArray(new Item[items.size()]));
        app.updateQuality();
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void delete(Item item){
        itemRepository.delete(item);
    }

    public Item findByName(String name) {
        try {
            return itemRepository.findByName(name).orElseThrow(() -> new ItemNotFoundException(ITEM_NOT_FOUND));
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
