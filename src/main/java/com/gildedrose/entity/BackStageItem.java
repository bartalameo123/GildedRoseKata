package com.gildedrose.entity;

public class BackStageItem {

    public static final int MAX_QUALITY = 50;
    private Item item;

    public BackStageItem(Item item){
        this.item = item;
    }

    public void updateQuality(){

        increaseQuality(item);
        if (backstageQualityDoubles(item.sellIn)) {
            increaseQuality(item);
        }

        if (backstageQualityTriples(item.sellIn)) {
            increaseQuality(item);
        }
        decreaseSellTime(item);
        if (sellDayHasPassed(item.sellIn)) {
            item.quality = 0;
        }
    }

    private boolean backstageQualityDoubles(int sellIn){
        return sellIn < 11;
    }

    public boolean itemQualityIsNotMaximal(int quality){
        return quality < MAX_QUALITY;
    }

    private boolean sellDayHasPassed(int sellIn){
        return sellIn < 0;
    }

    private boolean backstageQualityTriples(int sellIn){
        return sellIn < 6;
    }

    private void increaseQuality(Item item){
        if (itemQualityIsNotMaximal(item.quality))
            item.quality++;
    }

    private void decreaseSellTime(Item item){
        item.sellIn--;
    }

}
