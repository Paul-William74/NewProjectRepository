package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.About.ProductAdditionalInformation;
import com.Ecommerce.demo.DTO.Product.About.RecommendedProduct;
import com.Ecommerce.demo.DTO.Product.About.AboutProduct;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProduct;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductSize;
import com.Ecommerce.demo.DTO.Register.Product.ProductRegisterDTO;
import com.Ecommerce.demo.DTO.Register.Product.ProductSizeRegisterDTO;
import com.Ecommerce.demo.Exception.Enum.JewelleryTyeDoesNotExistException;
import com.Ecommerce.demo.Model.Product.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {
        SizeMapper.class
})
public abstract class ProductMapper {

    @Autowired private Formatter formatter;

}
