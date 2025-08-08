package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Cart.CartProduct;
import com.Ecommerce.demo.Model.Cart.CartItem;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductMaterial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Autowired
    private Formatter formatter;

    private String findImageFromMaterials(List<ProductMaterial> productMaterials, String material) {
        return productMaterials.stream()
                .filter(productMaterial -> productMaterial.getMaterial().getLabel().equalsIgnoreCase(material))
                .findFirst()
                .map(ProductMaterial::getImageList) //go into the ProductMaterial and get the list of images
                .map(images -> images.isEmpty() ? "" : images.get(0).getImgUrl())
                .orElseThrow(); // Get the first image or null if the list is empty
    }



    public String mapPrice(CartItem cartItem) {

        Product product = cartItem.getProductSize().getProduct();
        if(product.isOnDiscount()) {

            Double discountedPrice = product.getPrice() - (product.getPrice() * product.getDiscountPercentage() / 100);
            return formatter.getFormattedPrice(discountedPrice); //if the product is on discount
        }
        return formatter.getFormattedPrice(product.getPrice()); //if the product is not on discount
    }

    public int mapQuantity(CartItem cartItem) {
        return cartItem.getQuantity();
    }

    public String mapName(CartItem cartItem) {
        return cartItem.getProductSize().getProduct().getName();
    }

    public String mapImage(CartItem cartItem) {

        Product product = cartItem.getProductSize().getProduct();

        String image = "";
        List<MATERIAL> materials;
        return null;
    }

    public int mapSize(CartItem cartItem) {
        return cartItem.getProductSize().getSize();
    }

    @Mappings({
            @Mapping(target = "price", expression = "java(mapPrice(cartItem))"),
            @Mapping(target= "quantity", expression = "java(mapQuantity(cartItem))"),
            @Mapping(target = "name", expression = "java(mapName(cartItem))"),
            @Mapping(target = "size", expression = "java(mapSize(cartItem))"),

    })
    public abstract CartProduct toCartProduct(CartItem cartItem);

    public abstract List<CartProduct> toCartProducts(List<CartItem> cartItems);
}
