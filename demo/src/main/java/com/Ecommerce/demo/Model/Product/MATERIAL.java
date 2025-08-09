package com.Ecommerce.demo.Model.Product;
import lombok.Getter;

import java.util.Arrays;

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


    public static MATERIAL getMaterialFromLabel(String label) {
        return Arrays.stream(MATERIAL.values())
                .filter(material -> material.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Material : " + label + " Does not Exist"));
    }
}
