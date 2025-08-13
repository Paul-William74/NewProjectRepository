package com.Ecommerce.demo.Service.Product;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.Shop.ShopJewelleryShowcase;
import com.Ecommerce.demo.DTO.Product.Shop.ShopProduct;
import com.Ecommerce.demo.Model.Product.*;
import com.Ecommerce.demo.Repository.Product.ProductsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ProductsRepo productsRepo;
    private final Formatter formatter;

    public ResponseEntity<?> getJewelleryPiecesByType(String type) {

        JEWELERY_TYPE jeweleryType = JEWELERY_TYPE.getJewelleryTypeFromLabel(type);

        return null;
    }

    public ResponseEntity<?> getAllJewelleryTypes() {

        JEWELERY_TYPE[] jewelery_types = JEWELERY_TYPE.values();
        List<Product> products = this.productsRepo.findAll();

        Collections.shuffle(products);
        // Keep only the first product per jewellery type, skipping those with no prices
        Map<JEWELERY_TYPE, Product> productMap = products.stream()
                .filter(p ->  !p.getProductPrices().isEmpty()  &&
                        !p.getProductImages().isEmpty()
                        && p.isActive()) //only choose those that are active and complete to an extend
                .collect(LinkedHashMap::new,
                        (map, product) ->
                                map.putIfAbsent(product.getJeweleryType(), product),
                        LinkedHashMap::putAll);

        // Create a new list of products for response, each with one random price
        List<ShopJewelleryShowcase> responseProducts = productMap.values().stream()
                .limit(5)
                .map(product -> {

                    String imageUrl = product.getProductImages()
                            .stream()
                            .filter(img -> img.getImageType() == IMAGE_TYPE.PRIMARY
                            && product.equals(img.getProduct()))
                            .findFirst()
                            .map(ProductImage::getImgUrl)
                            .orElse(product.getName());


                    // Creating a DTO for response
                    return new ShopJewelleryShowcase(
                            product.getId(),
                            product.getJeweleryType().getType(),
                            imageUrl
                    );
                })
                .toList();

        return ResponseEntity.ok(responseProducts);
    }


    public ResponseEntity<?> getAllShopProducts() {

        List<Product> products = productsRepo.findAll();
        Collections.shuffle(products);

        List<ShopProduct> shopProducts = products.stream()
                .filter(p -> p.isActive() && !p.getProductPrices().isEmpty()
                        && !p.getProductImages().isEmpty())
                .map(product -> {

                    // Find first ProductPrice that is on sale
                    ProductPrice salePrice = product.getProductPrices().stream()
                            .filter(ProductPrice::isOnDiscount)
                            .findFirst()
                            .orElse(null);

                    boolean isOnSale = salePrice != null;

                    // Find images aligned with the sale material if on sale, else pick primary/secondary
                    List<String> imageUrls = product.getProductImages().stream()
                            .filter(img -> {
                                boolean matchesType = img.getImageType() == IMAGE_TYPE.PRIMARY
                                        || img.getImageType() == IMAGE_TYPE.SECONDARY;
                                boolean matchesMaterial = salePrice == null || img.getMaterial().equals(salePrice.getMaterial());
                                return matchesType && matchesMaterial;
                            })
                            .limit(2)
                            .map(ProductImage::getImgUrl)
                            .toList();

                    // Prepare the price to show
                    double priceToShow = salePrice != null ? salePrice.getBasePrice() : product.getProductPrices().get(0).getBasePrice();

                    ShopProduct shopProduct = new ShopProduct();
                    shopProduct.setId(product.getId());
                    shopProduct.setName(product.getName());
                    shopProduct.setImgUrls(imageUrls);
                    shopProduct.setPrice(formatter.getFormattedPrice(priceToShow));
                    shopProduct.setOnSale(isOnSale);

                    return shopProduct;
                })
                .toList();

        return ResponseEntity.ok(shopProducts);
    }

}
