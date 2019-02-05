package com.gildedrose.entity;

import java.util.Arrays;

public class GeneralItem extends Item {

    private final static int MAX_QUALITY = 50;
    private final static int MIN_QUALITY = 0;

    private Item item;

/*    static GeneralItem getQualifiedItem(Item item) {
        if(isBackstageItem(item)){
            return new BackStageItem(item);
        }
    }*/

    public GeneralItem(Item item) {
        super(item.name, item.sellIn, item.quality);
    }

    public void updateQuality() {

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) { //do not apply to sulfuras

            if (item.name.equals("Aged Brie") || item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                if (itemQualityIsNotMaximal(item.quality)) {  //max quality is 50
                    increaseQuality(item);  //increae for brie and passes

                    if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        getQualifiedItem(item).updateQuality();
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
                    item.quality = 0; //backstage quality is zero
                } else {
                    if (itemQualityIsNotMinimal(item.quality)) { //never negative
                        decreseQuality(item); //second decrease, as decreses twice as fast
                    }
                }
            }
        }
    }


    private boolean backstageQualityDoubles(int sellIn) {
        return sellIn < 11;
    }

    public boolean itemQualityIsNotMaximal(int quality) {
        return quality < MAX_QUALITY;
    }

    private boolean itemQualityIsNotMinimal(int quality) {
        return quality > MIN_QUALITY;
    }

    private boolean sellDayHasPassed(int sellIn) {
        return sellIn < 0;
    }

    private boolean backstageQualityTriples(int sellIn) {
        return sellIn < 6;
    }

    private void decreseQuality(Item item) {
        item.quality--;
    }

    private void increaseQuality(Item item) {
        item.quality++;
    }

    private void decreaseSellTime(Item item) {
        item.sellIn--;
    }

    private BackStageItem getQualifiedItem(Item item) {
        if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
            return new BackStageItem(item);
        }
        return null;
    }

    private static boolean isBackstageItem(Item item){
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
