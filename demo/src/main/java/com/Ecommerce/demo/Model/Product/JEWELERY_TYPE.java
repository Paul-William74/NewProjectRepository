package com.Ecommerce.demo.Model.Product;

import com.Ecommerce.demo.Exception.Enum.JewelleryTyeDoesNotExistException;
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

    public static JEWELERY_TYPE  getJewelleryTypeFromLabel(String label) throws JewelleryTyeDoesNotExistException {
        for(JEWELERY_TYPE jeweleryType: JEWELERY_TYPE.values())
            if(jeweleryType.type.equals(label))
                return jeweleryType;
        throw new JewelleryTyeDoesNotExistException("jewellery piece: " + label + " does not exist");
    }
}
