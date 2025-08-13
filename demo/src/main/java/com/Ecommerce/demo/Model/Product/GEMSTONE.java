package com.Ecommerce.demo.Model.Product;

import com.Ecommerce.demo.Exception.Enum.GemStoneDoesNotExistException;
import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public static GEMSTONE getGemStoneFromLabel(String label) {
        for(GEMSTONE gemstone: GEMSTONE.values())
            if(gemstone.label.equals(label))
                return gemstone;
        throw new GemStoneDoesNotExistException("Gem Stone: " + label + " does not exist");
    }

    public static Set<GEMSTONE> getGemStonesFromLabels(List<String> labels) {

        Set<GEMSTONE> gemstones = new HashSet<>();
        for(GEMSTONE gemstone: GEMSTONE.values())
            for(String labelToCompare: labels)
                if(gemstone.label.equals(labelToCompare))
                    gemstones.add(gemstone);
        return gemstones;
    }



}
