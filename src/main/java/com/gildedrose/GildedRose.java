package com.gildedrose;

import com.gildedrose.entity.Item;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { //do not apply to sulfuras
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {  //max quality is 50
                    items[i].quality = items[i].quality + 1;  //increae for brie and passes

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1; //+2 if <=10 days
                            }
                        }

                        if (items[i].sellIn < 6) { //+3 if less than 6 days
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) { //if sell day passed quality degrades twice as fast
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) { //never negative
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { //do not apply on sulfuras
                                items[i].quality = items[i].quality - 1; //second decrease
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) { //aged brie increases in quality
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}