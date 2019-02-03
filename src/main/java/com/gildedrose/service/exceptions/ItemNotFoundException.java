package com.gildedrose.service.exceptions;

public class ItemNotFoundException extends Exception{
    public final static String ITEM_NOT_FOUND = "Item with requested name has not been found!";

    public ItemNotFoundException(String message){
        super(message);
    }
}
