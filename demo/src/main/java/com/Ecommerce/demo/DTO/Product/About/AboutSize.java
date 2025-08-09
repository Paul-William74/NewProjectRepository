package com.Ecommerce.demo.DTO.Product.About;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AboutSize {

    private long id;

    private Integer size; //size of the jewelry
    private int quantity; // Quantity of the jewelery size
}
