package com.Ecommerce.demo.Model.Review;

import lombok.Getter;

@Getter
public enum LIKE_TYPE {
    LIKE("like"),
    DISLIKE("dislike");

    private final String label;

    LIKE_TYPE(String label) {
        this.label = label;
    }
}
