package com.Ecommerce.demo.DTO.Product.Admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AdminProductSize {

    private Long id;
    private Map<String, List<String>> materials;
}