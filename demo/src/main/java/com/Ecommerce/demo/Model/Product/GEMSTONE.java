package com.Ecommerce.demo.Model.Product;

import lombok.Getter;

@Getter
public enum GEMSTONE {

    DIAMOND("Diamond"),
    EMERALD("Emerald"),
    RUBY("Ruby"),
    SAPPHIRE("Sapphire"),
    AMETHYST("Amethyst"),
    PEARL("Pearl");

    private final String label;

    GEMSTONE(String label) {
        this.label = label;
    }
}
