package com.gildedrose.controller;

import com.gildedrose.entity.Item;
import com.gildedrose.service.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@Slf4j
public class ItemController {

    private final ItemServiceImpl itemServiceImpl;

    private AtomicInteger counter;

    @Autowired
    public ItemController(ItemServiceImpl itemServiceImpl){
        this.itemServiceImpl = itemServiceImpl;
    }

    @RequestMapping(value = "/getAllItemsList/", method = RequestMethod.GET)
    public DeferredResult<ResponseEntity<List<Item>>> getAllItemsList() {

        DeferredResult<ResponseEntity<List<Item>>> result = new DeferredResult<>();

        new Thread(() -> {
            try {
                result.setResult(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(itemServiceImpl.findAll()));
            } catch (Exception e) {
                log.error(e.getMessage());
                result.setResult(ResponseEntity.notFound().build());
            }
        }, "GetAllItemsList" + counter.incrementAndGet()).start();

        result.onTimeout(() ->
                result.setErrorResult(
                        ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                                .body("Getting Item list timeouted")));

        result.onError((Throwable throwable) ->
                result.setErrorResult(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Item list not available")));

        result.onCompletion(() ->
                log.info("Fetching item list {} completed", counter.get()));

        return result;

    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void updateQuality() {
        itemServiceImpl.updateItems();
    }

}
