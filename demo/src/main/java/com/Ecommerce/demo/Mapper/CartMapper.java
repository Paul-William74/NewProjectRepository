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
    @Autowired private Sorter sorter;


    public String mapPrice(CartItem cartItem) {

        Product product = cartItem.getProductSize().getProduct();
        if(product.isOnDiscount()) {

            Double discountedPrice = product.getBasePrice() - (product.getBasePrice() * product.getDiscountPercentage() / 100);
            return formatter.getFormattedPrice(discountedPrice); //if the product is on discount
        }
        return formatter.getFormattedPrice(product.getBasePrice()); //if the product is not on discount
    }

    public int mapQuantity(CartItem cartItem) {
        return cartItem.getQuantity();
    }

    public String mapName(CartItem cartItem) {
        return cartItem.getProductSize().getProduct().getName();
    }

    public String mapImage(CartItem cartItem) {
        return "";
    }

    public double mapSize(CartItem cartItem) {
        return cartItem.getProductSize().getSize();
    }

    public Set<String> mapMaterials(CartItem cartItem) {
        return cartItem.getMaterial().stream()
                .map(MATERIAL::getLabel)
                .collect(Collectors.toSet());
    }

    public Set<String> gemStones(CartItem cartItem) {
        return cartItem.getProductSize().getProduct().getGemStones()
                .stream()
                .map(GEMSTONE::getLabel)
                .collect(Collectors.toSet());
    }


    @Mappings({
            @Mapping(target = "price", expression = "java(mapPrice(cartItem))"),
            @Mapping(target= "quantity", expression = "java(mapQuantity(cartItem))"),
            @Mapping(target = "name", expression = "java(mapName(cartItem))"),
            @Mapping(target = "size", expression = "java(mapSize(cartItem))"),
            @Mapping(target = "imgUrl", expression = "java(mapImage(cartItem))"),
            @Mapping(target = "materials", expression = "java(mapMaterials(cartItem))"),
            @Mapping(target = "gemStones", expression = "java(gemStones(cartItem))")
    })
    public abstract CartProduct toCartProduct(CartItem cartItem);
    public abstract List<CartProduct> toCartProducts(List<CartItem> cartItems);
}
