package com.gildedrose.entity;

import java.util.Arrays;

public interface GeneralItem {

    int MAX_QUALITY = 50;
    int MIN_QUALITY = 0;

    static GeneralItem getItem(Item item) {
        if (isAgedBrieItem(item)) {
            return new AgedBrieItem(item);
        }
        return new StandardItem(item);
    }

    public void updateQuality();

    static boolean isBackstageItem(Item item){
        return item.name == "Backstage passes to a TAFKAL80ETC concert";
    }

    static boolean isSulfurItem(Item item){
        return item.name == "Sulfuras, Hand of Ragnaros";
    }

    static boolean isAgedBrieItem(Item item){
        return item.name == "Aged Brie";
    }

    static boolean isStandardItem(Item item){
        return item.name == "Aged Brie";
    }
}
