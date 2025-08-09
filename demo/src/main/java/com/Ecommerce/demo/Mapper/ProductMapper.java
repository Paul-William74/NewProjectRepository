package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.About.ProductAdditionalInformation;
import com.Ecommerce.demo.DTO.Product.About.RecommendedProduct;
import com.Ecommerce.demo.DTO.Product.About.AboutProduct;
import com.Ecommerce.demo.DTO.Register.ProductRegisterDTO;
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
    List<String> mapMaterials(List<ProductImage> productImages) {
        return productImages.stream()
                .map(productImage -> productImage.getMaterial().getLabel())
                .toList(); //take the material from the productImage
    }

    @Named("mapSizes")
    List<Double> mapSizes(List<ProductSize> productSizes) {
        return productSizes.stream()
                .map(ProductSize::getSize) //take all the sizes from the productSizes
                .toList(); // place them in a list
    }

    @Named("mapMaterialImageMap")
    Map<String, List<String>> mapMaterialImageMap(List<ProductImage> productImages) {
        return productImages.stream()
                .collect(Collectors.groupingBy(
                        productImage -> productImage.getMaterial().getLabel(), //group by material
                        Collectors.mapping(ProductImage::getImgUrl, Collectors.toList()) //map to a list of image urls
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
            @Mapping(target = "gemstones", source = "gemStones", qualifiedByName = "mapGemStones"), //map the gemstones to a list of strings
            @Mapping(target = "materials", source = "productImages", qualifiedByName = "mapMaterials"),//map the materials to a list of strings
            @Mapping(target = "sizes", source = "productSizes", qualifiedByName = "mapSizes") //map the sizes to a list of integers
    })
    public abstract ProductAdditionalInformation toProductAdditionalInformation(Product product);


    /// product about mapper
    @Mappings({
        @Mapping(target = "price", source = "basePrice", qualifiedByName = "priceMapping"), //map the price to a formatted string
        @Mapping(target = "discountPrice", source = "basePrice",qualifiedByName = "discountMapping"), //map the discounted price
        @Mapping(target = "materialImages", source = "productImages", qualifiedByName = "mapMaterialImageMap"),
        @Mapping(target = "gemstones", source = "gemStones", qualifiedByName = "mapGemStones"), //map the gemstones to a list of strings
    })
    public abstract AboutProduct toAboutProduct(Product product);

}
