package com.gildedrose.entity;

import static com.gildedrose.entity.GeneralItem.increaseQuality;
import static com.gildedrose.entity.GeneralItem.itemSellDatePassed;
import static com.gildedrose.entity.GeneralItem.decreaseSellTime;

public class AgedBrieItem implements GeneralItem{

    private Item item;

    public AgedBrieItem(Item item){
        this.item = item;
    }

    public void updateQuality() {
        increaseQuality(item);
        decreaseSellTime(item);
        if (itemSellDatePassed(item)) {
            increaseQuality(item);
        }
    }

}
