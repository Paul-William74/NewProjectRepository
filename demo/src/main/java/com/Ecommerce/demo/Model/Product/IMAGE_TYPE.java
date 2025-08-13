package com.Ecommerce.demo.Model.Product;

import com.Ecommerce.demo.Exception.Enum.ImageTypeDoesNotExistException;
import lombok.Getter;

@Getter
public enum IMAGE_TYPE {

    PRIMARY("Primary"),
    SECONDARY("Secondary");

    private final String label;

    IMAGE_TYPE(String label) {
        this.label = label;
    }

    public static IMAGE_TYPE fromLabel(String label) {
        for (IMAGE_TYPE type : values()) {
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }
        throw new ImageTypeDoesNotExistException("No IMAGE_TYPE with label '" + label + "' found.");
    }
}
