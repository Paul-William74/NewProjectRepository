package com.Ecommerce.demo.Model.Product;
import lombok.Getter;

@Getter
public enum MATERIAL {

    GOLD("Gold"),
    SILVER("Silver"),
    PLATINUM("Platinum"),
    E_WASTE("E-Waste"),
    STAINLESS_STEEL("Stainless Steel"),
    Titanium("Titanium"),
    COPPER("Copper"),
    TUNGSTEN("Tungsten");

    private final String label;

    MATERIAL(String label) {
        this.label = label;
    }
}
