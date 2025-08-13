package com.Ecommerce.demo.Service.User.Admin;


import com.Ecommerce.demo.DTO.Product.Admin.AdminProductPrice;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductPriceRequest;
import com.Ecommerce.demo.Mapper.ProductPriceMapper;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductPrice;
import com.Ecommerce.demo.Repository.Product.ProductPriceRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProductBasedCraud extends BaseService {

    private final ProductPriceRepo productPriceRepo;
    private final ProductPriceMapper productPriceMapper;

    public ResponseEntity<?> deleteProduct(Long product_id) {

        Product product = findProduct(product_id);
        if(product.isActive())
            product.setActive(false); //set the is-active to false
        else
            return ResponseEntity.badRequest().body("An Error Occurred");

        this.productsRepo.save(product); //save the changes
        return ResponseEntity.ok(product_id);//return the id of the product to remove
    }

    public ResponseEntity<?> bringBackProduct(Long productId) {

        Product product = findProduct(productId);

        if(!product.isActive())
            product.setActive(true); //set the is-active to false
        else
            return ResponseEntity.badRequest().body("An Error Occurred");

        this.productsRepo.save(product); //save the changes
        return ResponseEntity.ok("An entity of that product");//return the id of the product to remove

    }


    public ResponseEntity<?> editProduct() {
        return null;
    }

    public ResponseEntity<?> editProductPriceVariant(Long productPrice_id, AdminProductPriceRequest adminProductPriceRequest) {
        return this.productPriceRepo.findById(productPrice_id)
                .map(productPrice -> {

                    int count = 0;
                    if(!Objects.equals(adminProductPriceRequest.getPrice(), productPrice.getBasePrice())) {
                        productPrice.setBasePrice(adminProductPriceRequest.getPrice()); //set the price
                        count++;
                    }
                    if(!Objects.equals(adminProductPriceRequest.getDiscountPrice(), productPrice.getDiscountAmount())) {
                        productPrice.setDiscountAmount(adminProductPriceRequest.getDiscountPrice()); //set the discount amount
                        count++;
                    }
                    if(!Objects.equals(adminProductPriceRequest.getDiscountPercentage(), productPrice.getDiscountPercentage())) {
                        productPrice.setDiscountPercentage(adminProductPriceRequest.getDiscountPercentage()); //set the discount percentage
                        count++;
                    }
                    if(!productPrice.getMaterial().getLabel().equals(adminProductPriceRequest.getMaterial())) {
                        productPrice.setMaterial(MATERIAL.getMaterialFromLabel(adminProductPriceRequest.getMaterial())); //set the material
                        count++;
                    }
                    if(count> 1) { //if any changes occurred save them
                        ProductPrice editedPrice = this.productPriceRepo.save(productPrice); //save the changes
                        AdminProductPrice adminProductPrice = this.productPriceMapper.toAdminProductPrice(editedPrice); //transform that data into a dto
                        return ResponseEntity.ok(adminProductPrice);
                    }
                    return ResponseEntity.badRequest().body("An Error Occurred");
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteProductPriceVariant(Long productPrice_id) {
        return productPriceRepo.findById(productPrice_id)
                .map(productPrice -> {
                    if(productPrice.isActive()) {
                        productPrice.setActive(false);
                        this.productPriceRepo.save(productPrice);
                        return ResponseEntity.ok(productPrice_id);
                    }else
                        return ResponseEntity.badRequest().body("An Error Occurred");

                })
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

}
