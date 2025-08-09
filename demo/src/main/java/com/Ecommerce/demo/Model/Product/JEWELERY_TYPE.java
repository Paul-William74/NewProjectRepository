package com.Ecommerce.demo.Model.Product;

import lombok.Getter;

@Getter
public enum JEWELERY_TYPE {

    RING("Ring"),
    NECKLACE("Necklace"),
    BRACELET("Bracelet"),
    EARRINGS("Earrings");

    private final String type;

    JEWELERY_TYPE(String type) {
        this.type = type;
    }
}
