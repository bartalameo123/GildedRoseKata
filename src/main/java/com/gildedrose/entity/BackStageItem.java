package com.gildedrose.entity;

public class BackStageItem extends Item{
    private Item item;
    public BackStageItem(Item item){
        super(item.name, item.sellIn, item.quality);
    }

    public static BackStageItem gettQualifiedItem(Item item){
        return new BackStageItem(item);
    }

    public void updateQuality(){

        if(this.sellIn>0) {
            if (this.sellIn < 6) {
                this.quality = this.quality + 3;
            } else if (this.sellIn < 11) {
                this.quality = this.quality + 2;
            } else if (this.sellIn < 50) {
                this.quality = this.quality + 1;
            }
        }
        this.sellIn = this.sellIn--;
        if(sellIn < 0) {
            this.quality = 0;
        }
    }
    //custom backstage methods
}
