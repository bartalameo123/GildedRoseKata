package com.gildedrose;

import com.gildedrose.entity.GeneralItem;
import com.gildedrose.entity.Item;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items) {
            GeneralItem.getItem(item).updateQuality();

        }

    }

}
