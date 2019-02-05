package com.gildedrose.service;

import com.gildedrose.entity.GeneralItem;
import com.gildedrose.entity.Item;
import com.gildedrose.repository.ItemRepository;
import com.gildedrose.service.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

        CompletableFuture<List<Item>> items = CompletableFuture.supplyAsync(() -> itemRepository.findAll());

        CompletableFuture<Void> itemsProcess = items.thenRunAsync(() -> {
            try{
                items.get().stream().map(item -> GeneralItem.classifyItem(item)).forEach(GeneralItem::updateQuality);
            } catch (InterruptedException ie){
                log.error("Execution was interrupted. Error message: {}", ie.getMessage());
            } catch (ExecutionException ee){
                log.error("Execution has failed. Error message: {}", ee.getMessage());
            }

        });

        /*List<Item> items = itemRepository.findAll();
        GildedRose app = new GildedRose(items.toArray(new Item[items.size()]));
        app.updateQuality();*/
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
