package com.gildedrose.entity;

public interface BackStage extends GeneralItem{

    int SECOND_LEVEL = 10;
    int THIRD_LEVEL = 5;

    boolean increaseQualitySecondTime(Item item);

    boolean increaseQualityThirdTime(Item item);
}
