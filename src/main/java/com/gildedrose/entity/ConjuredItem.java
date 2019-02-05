package com.gildedrose.entity;

public class ConjuredItem implements GeneralItem{

    private Item item;

    public ConjuredItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {

    }
}
