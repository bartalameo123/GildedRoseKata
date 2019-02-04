package com.gildedrose;

import com.gildedrose.entity.Item;

import java.util.Arrays;

class GildedRose {
    Item[] items;

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
                if (item.quality < 50) {  //max quality is 50
                    item.quality = item.quality + 1;  //increae for brie and passes

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1; //+2 if <=10 days
                            }
                        }

                        if (item.sellIn < 6) { //+3 if less than 6 days
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            } else {

                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) { //if sell day passed quality degrades twice as fast
                if (item.name.equals("Aged Brie")) {
                    if (item.quality < 50) { //aged brie increases in quality
                        item.quality = item.quality + 1;
                    }
                } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    item.quality = 0;
                } else {
                    if (item.quality > 0) { //never negative
                        item.quality = item.quality - 1; //second decrease
                    }
                }
            }
        }
    }
}