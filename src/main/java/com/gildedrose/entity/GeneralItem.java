package com.gildedrose.entity;

import java.util.Arrays;

public interface GeneralItem {

    int MAX_QUALITY = 50;
    int MIN_QUALITY = 0;

    static GeneralItem getItem(Item item) {
        switch (item.name){
            case "Aged Brie":
                return new AgedBrieItem(item);
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurItem(item);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackStageItem(item);
            case "Conjured":
                return new ConjuredItem(item);
            default:
                return new StandardItem(item);
        }

    }

    void updateQuality();

}
