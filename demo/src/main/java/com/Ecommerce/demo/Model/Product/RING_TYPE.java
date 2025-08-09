package com.Ecommerce.demo.Model.Product;

import lombok.Getter;

@Getter
public enum RING_TYPE {

    PROMISE("Promise"),
    WEDDING("Wedding"),
    ENGAGEMENT("Engagement"),
    CASUAL("Casual");

    private final String label;

    RING_TYPE(String label) {
        this.label = label;
    }
}
