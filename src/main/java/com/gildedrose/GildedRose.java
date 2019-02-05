package com.gildedrose;

import com.gildedrose.entity.AgedBrieItem;

import com.gildedrose.entity.BackStageItem;
import com.gildedrose.entity.GeneralItem;
import com.gildedrose.entity.Item;
import com.gildedrose.entity.StandardItem;
import com.gildedrose.entity.SulfurItem;


public class GildedRose {
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
            if (isSulfurItem(item)) {
                SulfurItem sulfurItem = new SulfurItem(item);
                sulfurItem.updateQuality();
                continue;
            }
            if (isAgedBrieItem(item)) {
                GeneralItem.getItem(item).updateQuality();
              //  AgedBrieItem agedBrieItem = new AgedBrieItem(item);
              //  agedBrieItem.updateQuality();
                continue;
            }
            if (isBackstageItem(item)) {
                BackStageItem backStageItem = new BackStageItem(item);
                backStageItem.updateQuality();
                continue;
            }

            StandardItem standardItem = new StandardItem(item);
            standardItem.updateQuality();
        }

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


}
