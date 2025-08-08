package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.About.ProductAdditionalInformation;
import com.Ecommerce.demo.DTO.Product.About.RecommendedProduct;
import com.Ecommerce.demo.DTO.Product.About.AboutProduct;
import com.Ecommerce.demo.DTO.Register.ProductRegisterDTO;
import com.Ecommerce.demo.Model.Product.*;
import jdk.jfr.Name;
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


    abstract Product toProduct(ProductRegisterDTO productRegisterDTO);

    abstract RecommendedProduct toRecommendedProduct(Product product);

    abstract List<RecommendedProduct> toRecommendedProducts(List<Product> products);

    @Named("mapGemStones")
    List<String> mapGemStones(Set<GEMSTONE> gemstones) {
        return gemstones.stream()
                .map(GEMSTONE::getLabel)
                .toList();
    }

    @Named("mapMaterials")
    List<String> mapMaterials(List<ProductMaterial> productMaterials) {
        return productMaterials.stream()
                .map(ProductMaterial::getMaterial) //take all the materials from the productMaterials
                .map(MATERIAL::getLabel)//take the label of those materials
                .toList(); // place them in a list
    }

    @Named("mapSizes")
    List<Integer> mapSizes(List<ProductSize> productSizes) {
        return productSizes.stream()
                .map(ProductSize::getSize) //take all the sizes from the productSizes
                .toList(); // place them in a list
    }

    @Named("mapMaterialImageMap")
    Map<String, List<String>> mapMaterialImageMap(List<ProductMaterial> productMaterials) {
        return productMaterials.stream()
                .collect(Collectors.toMap(
                        pm -> pm.getMaterial().getLabel(),                        // key: material label
                        pm -> pm.getImageList().stream()
                                .map(ProductImage::getImgUrl)
                                .collect(Collectors.toList()),                   // value: list of image URLs
                        (existing, replacement) -> existing                      // in case of duplicate keys
                ));
    }

    @Named("discountMapping")
    String mapDiscount(Double discount) {
        return this.formatter.getFormattedPrice(discount);
    }

    @Named("priceMapping")
    String mapPrice(Double price) {
        return this.formatter.getFormattedPrice (price);
    }




    /// product additional information mapper
    @Mappings({
            @Mapping(target = "gemstones", source = "gemstones", qualifiedByName = "mapGemStones"), //map the gemstones to a list of strings
            @Mapping(target = "materials", source = "productMaterials", qualifiedByName = "mapMaterials"),//map the materials to a list of strings
            @Mapping(target = "sizes", source = "productSizes", qualifiedByName = "mapSizes") //map the sizes to a list of integers
    })
    public abstract ProductAdditionalInformation toProductAdditionalInformation(Product product);


    /// product about mapper
    @Mappings({
        @Mapping(target = "price", source = "price", qualifiedByName = "priceMapping"), //map the price to a formatted string
        @Mapping(target = "discountPrice", source = "price",qualifiedByName = "discountMapping"), //map the discounted price
        @Mapping(target = "materialImages", source = "productMaterials", qualifiedByName = "mapMaterialImageMap"),
        @Mapping(target = "gemstones", source = "gemstones", qualifiedByName = "mapGemStones"), //map the gemstones to a list of strings
    })
    public abstract AboutProduct toAboutProduct(Product product);

}
