package com.gildedrose.entity;

public class AgedBrieItem {

    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;

    private Item item;

    public AgedBrieItem(Item item){
        this.item = item;
    }

    public void updateQuality() {
        increaseQuality(item);
        decreaseSellTime(item);
        if (sellDayHasPassed(item.sellIn)) {
            increaseQuality(item);
        }
    }

    public boolean itemQualityIsNotMaximal(int quality){
        return quality < MAX_QUALITY;
    }

    private boolean itemQualityIsNotMinimal(int quality){
        return quality > MIN_QUALITY;
    }

    private boolean sellDayHasPassed(int sellIn){
        return sellIn < 0;
    }

    private void increaseQuality(Item item){
        if (itemQualityIsNotMaximal(item.quality))
            item.quality++;
    }

    private void decreaseSellTime(Item item){
        item.sellIn--;
    }

}
