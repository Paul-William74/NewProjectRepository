package com.Ecommerce.demo.Mapper;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.About.AboutSize;
import com.Ecommerce.demo.DTO.Product.About.ProductAdditionalInformation;
import com.Ecommerce.demo.DTO.Product.About.RecommendedProduct;
import com.Ecommerce.demo.DTO.Product.About.AboutProduct;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProduct;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductSize;
import com.Ecommerce.demo.DTO.Register.ProductRegisterDTO;
import com.Ecommerce.demo.Model.Product.*;
import com.Ecommerce.demo.Model.User.Admin;
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


    public abstract Product toProduct(ProductRegisterDTO productRegisterDTO);

    public abstract RecommendedProduct toRecommendedProduct(Product product);

    public abstract List<RecommendedProduct> toRecommendedProducts(List<Product> products);


    public AdminProduct toAdminProduct(Product product) {

        AdminProduct adminProduct = new AdminProduct(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getJeweleryType().getType()
        );
        for(ProductSize productSize: product.getProductSizes())
            adminProduct.getProductSizes().add(
                    new AdminProductSize(
                            productSize.getId(),
                            getSizesWithMaterials(productSize)
                    )
            );
        return adminProduct;
    }

    private Map<String, List<String>> getSizesWithMaterials(ProductSize productSize) {
        return productSize.getProductVariants().stream()
                .collect(Collectors.groupingBy(
                        variant -> variant.getMaterial().getLabel(),
                        Collectors.mapping(
                                variant ->
                                        String.valueOf(productSize.getSize() % 1 == 0 ?
                                                productSize.getSize().intValue() :  //if there's no remainder store the integer
                                                productSize.getSize()), //if there's a remainder store it with the remainder
                                Collectors.toList()
                        )
                ));
    }

    public abstract List<AdminProduct> toAdminProducts(List<Product> products);

}
