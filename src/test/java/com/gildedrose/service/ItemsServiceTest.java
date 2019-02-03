package com.gildedrose.service;

import com.gildedrose.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class ItemsServiceTest {

    private final ItemService itemService;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public ItemsServiceTest(
            ItemService itemService,
            ElasticsearchTemplate elasticsearchTemplate
    ) {
        this.itemService = itemService;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Test
    public void testSave() {
        Item item = new Item("TestItem", 5, 4);
        itemService.save(item);

        assertEquals("TestItem", item.getName());
        assertEquals(5, item.sellIn);
        assertEquals(4, item.getQuality());

    }

    @Test
    public void testDelete() {

        Item item = new Item("TestItem", 5, 4);
        itemService.save(item);
        itemService.delete(item);
        Item itemRepo = itemService.findByName(item.getName());
        assertNull(itemRepo);
    }
}
