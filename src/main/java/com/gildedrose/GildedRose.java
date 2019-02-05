package com.gildedrose;

import com.gildedrose.entity.GeneralItem;
import com.gildedrose.entity.Item;

import java.util.Arrays;

public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        Arrays.asList(items).stream().map(item -> GeneralItem.getItem(item)).forEach(GeneralItem::updateQuality);

    }

}
