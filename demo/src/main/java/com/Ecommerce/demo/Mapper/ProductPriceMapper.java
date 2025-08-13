package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductPrice;
import com.Ecommerce.demo.DTO.Product.Compare.ProductCompareDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductPriceRegisterDTO;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductImage;
import com.Ecommerce.demo.Model.Product.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProductPriceMapper {

    @Autowired
    private Formatter formatter;


    private Map<String, String> mapMaterialWithCosts(Product product) {
        return product.getProductPrices()
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getMaterial().getLabel(), // key = material label
                        p -> formatter.getFormattedPrice(p.getBasePrice()), // value = formatted price
                        (v1, v2) -> v1, // merge function in case of duplicate keys
                        LinkedHashMap::new // preserve insertion order
                ));
    }

    private Map<String, String> mapDiscountedMaterials(Product product) {
        return product.getProductPrices()
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getMaterial().getLabel(), // key = material label
                        p -> formatter.getFormattedPrice(
                                p.getDiscountAmount()
                        ), // use discount if available
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }


    @Named("mapMaterial")
    public MATERIAL mapMaterial(String material) {
        return MATERIAL.getMaterialFromLabel(material);
    }

    @Named("mapPrice")
    public String mapPrice(Double price) {
        if(price != null)
            return formatter.getFormattedPrice(price);
        return "R0.00";
    }

    @Named("mapDiscountPercentage")
    public String mapDiscountAmount(Double discountPercentage) {
        if(discountPercentage != null)
            return String.valueOf(discountPercentage * 100) + '%';
        return "";
    }

    @Mapping(target = "material", source = "material" , qualifiedByName = "mapMaterial")
    public abstract ProductPrice toProductPrice(ProductPriceRegisterDTO productRegisterPriceDTO);


    @Mapping(target = "price", source = "basePrice", qualifiedByName = "mapPrice")
    @Mapping(target = "discountPrice", source = "discountAmount", qualifiedByName = "mapPrice")
    @Mapping(target = "discountPercentage", source = "discountPercentage", qualifiedByName = "mapDiscountPercentage")
    public abstract AdminProductPrice toAdminProductPrice(ProductPrice productPrice);



    public List<ProductCompareDTO> productCompareDTOs(Product product, ProductImage productImage, ProductPrice productPrice)  {

        return product.getProductImages().stream()
                .map(image -> {
                    ProductCompareDTO dto = new ProductCompareDTO();
                    dto.setName(product.getName());
                    dto.setPrice(formatter.getFormattedPrice(productPrice.getBasePrice()));
                    dto.setImgUrl(productImage.getImgUrl());
                    dto.setMaterialWithCosts(mapMaterialWithCosts(product));
                    dto.setMaterialOnDiscount(mapDiscountedMaterials(product));
                    dto.setDiscountPrice("not yet");
                    return dto;
                })
                .toList();

    }






}
