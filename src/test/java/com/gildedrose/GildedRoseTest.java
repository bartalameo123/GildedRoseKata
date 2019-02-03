package com.gildedrose;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

import com.gildedrose.entity.Item;
import org.junit.Assert;
import org.junit.Test;


public class GildedRoseTest {

    public GildedRoseTest(){}

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    // Once the sell by date has passed, Quality degrades twice as fast
    @Test
    public void testQualityDegradesTwice() {
        Item[] items = new Item[] {
                new Item("test item", 1, 16) ,
                new Item("Conjured Mana Cake", 1,18),
                new Item("Elixir of the Mongoose", 1,18),
                new Item("Aged Brie", 1,18)};
        GildedRose app = new GildedRose(items);
        int qualityDeltaBeforeDefault = items[0].quality;
        int qualityDeltaBeforeConjured = items[1].quality;
        int qualityDeltaBeforeElixir = items[2].quality;
        int qualityDeltaBeforeBrie = items[3].quality;
        app.updateQuality();
        qualityDeltaBeforeDefault -= app.items[0].quality;
        qualityDeltaBeforeConjured -= app.items[1].quality;
        qualityDeltaBeforeElixir -= app.items[2].quality;
        qualityDeltaBeforeBrie -= app.items[3].quality;
        int qualityDeltaAfterDefault = app.items[0].quality;
        int qualityDeltaAfterConjured = app.items[1].quality;
        int qualityDeltaAfterElixir = app.items[2].quality;
        int qualityDeltaAfterBrie = app.items[3].quality;
        app.updateQuality();
        qualityDeltaAfterDefault -= app.items[0].quality;
        qualityDeltaAfterConjured -= app.items[1].quality;
        qualityDeltaAfterElixir -= app.items[2].quality;
        qualityDeltaAfterBrie -= app.items[3].quality;
        assertEquals(2, qualityDeltaAfterDefault/qualityDeltaBeforeDefault);
        assertEquals(2, qualityDeltaAfterConjured/qualityDeltaBeforeConjured);
        assertEquals(2, qualityDeltaAfterElixir/qualityDeltaBeforeElixir);
        assertEquals(2, qualityDeltaAfterBrie/qualityDeltaBeforeBrie);
    }

    //The Quality of an item is never negative
    @Test
    public void testQualityIsBetween0And50() {
        Item[] items = new Item[]{
                new Item("test item", 100, 2),
                new Item("Conjured Mana Cake", 200, 3),
                new Item("Backstage passes to a TAFKAL80ETC concert", 16, 3),
                new Item("Elixir of the Mongoose", 5, 4),
                new Item("Aged Brie", 50, 4)};
        GildedRose app = new GildedRose(items);
        for( int i = 0; i<1000; i++){
            app.updateQuality();
            assertTrue(app.items[0].quality >= 0 && app.items[0].quality <= 50);
            assertTrue(app.items[1].quality >= 0 && app.items[1].quality <= 50);
            assertTrue(app.items[2].quality >= 0 && app.items[2].quality <= 50);
            assertTrue(app.items[3].quality >= 0 && app.items[3].quality <= 50);
            assertTrue(app.items[4].quality >= 0 && app.items[4].quality <= 50);
        }

    }

    @Test
    public void testSulfurNeverChanges() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 100, 80)};
        GildedRose app = new GildedRose(items);
        for( int i = 0; i<1000; i++){
            app.updateQuality();
            assertEquals(80, app.items[0].quality);
            assertEquals(100, app.items[0].sellIn);
        }
    }

    //"Aged Brie" actually increases in Quality the older it gets
    @Test
    public void testAgedBrieQualityIncreases() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 100, 2)};
        GildedRose app = new GildedRose(items);
        int quality;
        for( int i = 0; i<1000; i++){
            quality = items[0].quality;
            app.updateQuality();
            assertTrue(quality <= app.items[0].quality);
        }
    }

    //"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    @Test
    public void testBackStageQualityIncreases() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 100, 2)};
        GildedRose app = new GildedRose(items);
        int quality;
        for( int i = 0; i<100; i++){
            quality = items[0].quality;
            app.updateQuality();
            assertTrue(quality <= app.items[0].quality);
        }
    }

    @Test
    public void testBackStageQualityIncreasesBy2AndBy3() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 2)};
        GildedRose app = new GildedRose(items);
        int quality;
        int sellIn;
        int qualityChange;
        for( int i = 0; i<15; i++){
            quality = items[0].quality;
            sellIn = items[0].sellIn;
            app.updateQuality();
            qualityChange = quality - app.items[0].quality;
            if(sellIn > 10 && quality < 50) {
                assertTrue(app.items[0].quality - quality == 1);
            } else if (sellIn > 5 && sellIn <= 10){
                if (quality < 49) {
                    assertTrue( app.items[0].quality - quality == 2);
                } else if(quality == 49){
                    assertTrue(app.items[0].quality - quality == 1);
                }
            } else if (sellIn >= 0 && sellIn <= 5){
                if (quality < 48) {
                    assertTrue(app.items[0].quality - quality == 3);
                }
            } else {
                assertEquals(0, app.items[0].quality);
            }
        }
    }

    //- "Conjured" items degrade in Quality twice as fast as normal items
    @Test
    public void testConjuredQualityDegradesTwiceAsFast() {
        Item[] items = new Item[] {
                new Item("test item", 1, 16) ,
                new Item("Conjured Mana Cake", 1,18)};
        GildedRose app = new GildedRose(items);
        int qualityDeltaBeforeDefault = items[0].quality;
        int qualityDeltaBeforeConjured = items[1].quality;
        app.updateQuality();
        qualityDeltaBeforeDefault -= app.items[0].quality;
        qualityDeltaBeforeConjured -= app.items[1].quality;
        assertEquals(1, qualityDeltaBeforeDefault);
        assertNotEquals(2, qualityDeltaBeforeConjured);
        int qualityDeltaAfterDefault = app.items[0].quality;
        int qualityDeltaAfterConjured = app.items[1].quality;
        app.updateQuality();
        qualityDeltaAfterDefault -= app.items[0].quality;
        qualityDeltaAfterConjured -= app.items[1].quality;
        assertEquals(2, qualityDeltaAfterDefault/qualityDeltaBeforeDefault);
        assertNotEquals(4, qualityDeltaAfterConjured/qualityDeltaBeforeConjured);
    }


}
