package com.gildedrose.entity;

import static com.gildedrose.entity.GeneralItem.increaseQuality;
import static com.gildedrose.entity.GeneralItem.itemSellDatePassed;
import static com.gildedrose.entity.GeneralItem.decreaseSellTime;

public class BackStageItem implements BackStage {

    private Item item;

    public BackStageItem(Item item){
        this.item = item;
    }

    public void updateQuality(){

        increaseQuality(item);

        if (increaseQualitySecondTime(item)) {
            increaseQuality(item);
        }

        if (increaseQualityThirdTime(item)) {
            increaseQuality(item);
        }

        decreaseSellTime(item);

        if (itemSellDatePassed(item)) {
            item.quality = 0;
        }
    }

    @Override
    public boolean increaseQualitySecondTime(Item item) {
        return item.sellIn <= BackStage.SECOND_LEVEL ;
    }

    @Override
    public boolean increaseQualityThirdTime(Item item) {
        return item.sellIn <= BackStage.THIRD_LEVEL;
    }
}
