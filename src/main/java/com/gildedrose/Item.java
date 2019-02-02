package com.gildedrose;

public class Item {

    public String name;
//number of days to sell the item (lower by the end of the day)
    public int sellIn;
//how valuable the item is (lower at the end of the day) never negative(0-50)
    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }


   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
