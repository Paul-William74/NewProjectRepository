package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.About.AboutSize;
import com.Ecommerce.demo.Model.Product.ProductSize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SizeMapper {




    abstract AboutSize toAboutSize(ProductSize productSize);
    abstract List<AboutSize> toAboutSizes(List<ProductSize> productSizes);


}
