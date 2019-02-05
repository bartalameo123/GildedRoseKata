package com.gildedrose.entity;

public class StandardItem implements GeneralItem{

    private Item item;

    public StandardItem(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        decreseQuality(item);

        decreaseSellTime(item);

        if (sellDayHasPassed(item.sellIn)) {
            decreseQuality(item);
        }
    }

    private boolean itemQualityIsNotMinimal(int quality){
        return quality > GeneralItem.MIN_QUALITY;
    }

    private boolean sellDayHasPassed(int sellIn){
        return sellIn < 0;
    }

    private void decreseQuality(Item item){
        if (itemQualityIsNotMinimal(item.quality))
            item.quality--;
    }

    private void decreaseSellTime(Item item){
        item.sellIn--;
    }
}
