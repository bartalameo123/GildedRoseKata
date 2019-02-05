package com.gildedrose.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@Document(indexName = "gildedroseproperties")
public class ItemProperties {

    @Id
    private String name;
    private int standardQualityDelta;
    private int daysIncreaseChange;

}
