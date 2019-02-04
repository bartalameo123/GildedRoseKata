package com.gildedrose;

import com.gildedrose.entity.Item;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    private final static int MAX_QUALITY = 50;
    private final static int MIN_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

   //     Arrays.asList(items).stream().
        for (Item item:items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")){ //do not apply to sulfuras
                continue;
            }
            if (item.name.equals("Aged Brie")
                    || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (itemQualityIsNotMaximal(item.quality)) {  //max quality is 50
                    increaseQuality(item);  //increae for brie and passes

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (backstageQualityDoubles(item.sellIn)) {
                            if (itemQualityIsNotMaximal(item.quality)) {
                                increaseQuality(item); //+2 if <=10 days
                            }
                        }

                        if (backstageQualityTriples(item.sellIn)) { //+3 if less than 6 days
                            if (itemQualityIsNotMaximal(item.quality)) {
                                increaseQuality(item);
                            }
                        }
                    }
                }
            } else if (itemQualityIsNotMinimal(item.quality)) {
                decreseQuality(item);
            }

            decreaseSellTime(item);



            if (sellDayHasPassed(item.sellIn)) { //if sell day passed quality degrades twice as fast
                if (item.name.equals("Aged Brie")) {
                    if (itemQualityIsNotMaximal(item.quality)) { //aged brie increases in quality
                        increaseQuality(item);
                    }
                } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else {
                    if (itemQualityIsNotMinimal(item.quality)) { //never negative
                        decreseQuality(item); //second decrease
                    }
                }
            }
        }
    }

    private boolean backstageQualityDoubles(int sellIn){
        return sellIn < 11;
    }

    private boolean itemQualityIsNotMaximal(int quality){
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
        item.quality--;
    }

    private void increaseQuality(Item item){
        item.quality++;
    }

    private void decreaseSellTime(Item item){
        item.sellIn--;
    }
}