package com.gildedrose.service;

import com.gildedrose.GildedRoseApp;
import com.gildedrose.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GildedRoseApp.class)
public class ItemsServiceTest {

    @Autowired
    private ItemService itemServiceImpl;

    @Test
    public void contextLoads() {

    };
 //   @Autowired
 //   private ElasticsearchTemplate elasticsearchTemplate;

/*    @Test
    public void testSave() {
        Item item = new Item("TestItem", 5, 4);
        itemServiceImpl.save(item);

        assertEquals("TestItem", item.getName());
        assertEquals(5, item.sellIn);
        assertEquals(4, item.getQuality());

    }

    @Test
    public void testDelete() {

        Item item = new Item("TestItem", 5, 4);
        itemServiceImpl.save(item);
        itemServiceImpl.delete(item);
        Item itemRepo = itemServiceImpl.findByName(item.getName());
        assertNull(itemRepo);
    }*/
}
