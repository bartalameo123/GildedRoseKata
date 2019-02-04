package com.gildedrose;

import com.gildedrose.entity.BackStageItem;
import com.gildedrose.entity.GeneralItem;
import com.gildedrose.entity.Item;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.gildedrose.entity.BackStageItem.gettQualifiedItem;

class GildedRose {
    Item[] items;

    private final static int MAX_QUALITY = 50;
    private final static int MIN_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

    /*    Arrays.asList(items).stream().map(tmp -> {GeneralItem generalItem = new GeneralItem(tmp); return generalItem;})
                .forEach(generalItem -> generalItem.updateQuality())
        ;*/

        for (Item item : items) {
            if (isSulfurItem(item)) { //do not apply to sulfuras
                continue;
            }
            if (isAgedBrieItem(item)) {
                increaseQuality(item);
                decreaseSellTime(item);
                if (sellDayHasPassed(item.sellIn)) {
                    increaseQuality(item);
                }
                continue;
            }
            if (isBackstageItem(item)) {
                increaseQuality(item);  //increae for brie and passes
                if (backstageQualityDoubles(item.sellIn)) {
                    increaseQuality(item); //+2 if <=10 days
                }

                if (backstageQualityTriples(item.sellIn)) { //+3 if less than 6 days
                    increaseQuality(item);
                }
                decreaseSellTime(item);
                if (sellDayHasPassed(item.sellIn)) {
                    item.quality = 0; //backstage quality is zero
                }
                continue;
            }

            decreseQuality(item);

            decreaseSellTime(item);

            if (sellDayHasPassed(item.sellIn)) { //if sell day passed quality degrades twice as fast
                decreseQuality(item); //second decrease, as decreses twice as fast
            }
        }

    }

    private boolean backstageQualityDoubles(int sellIn){
        return sellIn < 11;
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

    private boolean backstageQualityTriples(int sellIn){
        return sellIn < 6;
    }

    private void decreseQuality(Item item){
        if (itemQualityIsNotMinimal(item.quality))
            item.quality--;
    }

    private void increaseQuality(Item item){
        if (itemQualityIsNotMaximal(item.quality))
            item.quality++;
    }

    private void decreaseSellTime(Item item){
        item.sellIn--;
    }

    private BackStageItem getQualifiedItem(Item item){
        if(item.name=="Backstage passes to a TAFKAL80ETC concert"){
            return new BackStageItem(item);
        }
        return null;
    }

    private boolean isBackstageItem(Item item){
         return item.name == "Backstage passes to a TAFKAL80ETC concert";
    }

    private boolean isSulfurItem(Item item){
        return item.name == "Sulfuras, Hand of Ragnaros";
    }

    private boolean isAgedBrieItem(Item item){
        return item.name == "Aged Brie";
    }

    private boolean isStandardItem(Item item){
        return item.name == "Aged Brie";
    }

}
