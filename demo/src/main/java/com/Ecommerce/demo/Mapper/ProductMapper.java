package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Register.Product.ProductRegisterDTO;
import com.Ecommerce.demo.Model.Product.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired private Formatter formatter;


    @Named("mapGemStones")
    public Set<GEMSTONE> mapGemStones(List<String> gemStones) {
        return gemStones.stream()
            .map(GEMSTONE::getGemStoneFromLabel)
            .collect(Collectors.toSet());
    }

    @Named("mapJewelleryType")
    public JEWELERY_TYPE mapJewelleryType(String jewelleryType) {
        return JEWELERY_TYPE.getJewelleryTypeFromLabel(jewelleryType);
    }

    @Mapping(target = "gemStones", source = "gemStones", qualifiedByName = "mapGemStones")
    @Mapping(target = "jeweleryType", source = "jeweleryType", qualifiedByName = "mapJewelleryType")
    public abstract Product toProduct(ProductRegisterDTO productRegisterDTO);



}
