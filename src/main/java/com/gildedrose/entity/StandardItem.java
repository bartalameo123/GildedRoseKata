package com.gildedrose.entity;

public class StandardItem implements GeneralItem{

    private Item item;

    public StandardItem(Item item) {
        this.item = item;
    }

    public void updateQuality() {

        GeneralItem.decreaseQuality(item);
        GeneralItem.decreaseSellTime(item);
        if(GeneralItem.itemSellDatePassed(item)){
            GeneralItem.decreaseQuality(item);
        }

    }

}
