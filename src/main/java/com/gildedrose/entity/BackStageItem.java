package com.gildedrose.entity;

public class BackStageItem extends Item{

    public String name;
    public int sellIn;
    public int quality;

    public BackStageItem(String name, int sellIn, int quality){
        super(name,sellIn,quality);
    }


    //custom backstage methods
}
