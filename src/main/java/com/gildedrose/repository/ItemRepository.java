package com.gildedrose.repository;

import com.gildedrose.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends MongoRepository<Item, String> {

    Optional<Item> findByName(String name);

    List<Item> findAll();

}
