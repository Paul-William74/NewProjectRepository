package com.Ecommerce.demo.Model.Product;

import com.Ecommerce.demo.Exception.Enum.JewelleryTyeDoesNotExistException;
import com.Ecommerce.demo.Exception.Enum.MaterialDoesNotExistException;
import lombok.Getter;

import java.util.Arrays;

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

    public static JEWELERY_TYPE  getJewelleryTypeFromLabel(String label) throws JewelleryTyeDoesNotExistException {
        return Arrays.stream(JEWELERY_TYPE.values())
                .filter( material -> material.getType().equals(label))
                .findFirst()
                .orElseThrow(() -> new JewelleryTyeDoesNotExistException("Jewellery Type: " + label + " Does not Exist"));
    }
}
