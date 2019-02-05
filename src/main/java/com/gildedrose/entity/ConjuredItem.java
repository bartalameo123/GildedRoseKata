package com.gildedrose.entity;

import static com.gildedrose.entity.GeneralItem.decreaseQuality;
import static com.gildedrose.entity.GeneralItem.itemSellDatePassed;
import static com.gildedrose.entity.GeneralItem.decreaseSellTime;

public class ConjuredItem implements GeneralItem{

    private static final int CHANGE_SPEED = 2;

    private Item item;

    public ConjuredItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateQuality() {

        decreaseSellTime(item);
        for(int i = 0; i < CHANGE_SPEED; i++){
            decreaseQuality(item);
            if(itemSellDatePassed(item)){
                decreaseQuality(item);
            }

        }

    }
}
