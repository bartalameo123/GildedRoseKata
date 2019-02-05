package com.gildedrose.entity;

public interface GeneralItem {

    int MAX_QUALITY = 50;
    int MIN_QUALITY = 0;

    static GeneralItem classifyItem(Item item) {
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

    static boolean itemQualityCanBeChanged(Item item){
        return item.name == "Sulfuras, Hand of Ragnaros" ? false : MIN_QUALITY < item.quality && item.quality < MAX_QUALITY;
    }

    static void increaseQuality(Item item){
        if(itemQualityCanBeChanged(item)) {
            item.quality++;
        }
    }

    static void decreaseQuality(Item item){
        if(itemQualityCanBeChanged(item)) {
            item.quality--;
        }
    }

    static void decreaseSellTime(Item item){
        item.sellIn--;
    }

    static boolean itemSellDatePassed(Item item){
        return item.sellIn < 0;
    }

    void updateQuality();

}
