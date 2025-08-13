package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.Components.Sorter;
import com.Ecommerce.demo.DTO.Cart.CartProduct;
import com.Ecommerce.demo.Model.Cart.CartItem;
import com.Ecommerce.demo.Model.Product.GEMSTONE;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Autowired private Formatter formatter;


}
