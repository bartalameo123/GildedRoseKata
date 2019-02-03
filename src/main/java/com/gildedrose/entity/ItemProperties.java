package com.gildedrose.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@Document(indexName = "GildedRose")
public class ItemProperties {

    public static final int MIN_QUALITY = 0;
    public static final int MAX_QUALITY = 50;

    @Id
    public String name;
    private int qualityDelta;

}
