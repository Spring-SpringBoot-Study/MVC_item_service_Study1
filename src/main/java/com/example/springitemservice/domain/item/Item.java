package com.example.springitemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // price가 null인 경우도 있다고 고려해서 int가 아닌 Integer
    private Integer quantity;

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
