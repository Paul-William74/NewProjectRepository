package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.DTO.Register.Product.ProductPriceRegisterDTO;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.ProductPrice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPriceMapper {

    default MATERIAL mapMaterial(String material) {
        return MATERIAL.getMaterialFromLabel(material);
    }

    ProductPrice toProductPrice(ProductPriceRegisterDTO productRegisterPriceDTO);


}
