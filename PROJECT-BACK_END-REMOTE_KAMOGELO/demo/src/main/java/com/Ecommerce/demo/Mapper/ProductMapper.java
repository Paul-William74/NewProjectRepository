package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.DTO.Register.ProductRegisterDTO;
import com.Ecommerce.demo.Model.Product.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductRegisterDTO productRegisterDTO);


}
