package com.gildedrose.repository;

import com.gildedrose.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends ElasticsearchRepository<Item, String> {

    Optional<Item> findByName(String name);

    List<Item> findAll();

}
