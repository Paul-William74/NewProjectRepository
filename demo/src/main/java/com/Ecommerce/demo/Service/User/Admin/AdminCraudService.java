package com.Ecommerce.demo.Service.User.Admin;


import com.Ecommerce.demo.DTO.Product.Admin.AdminProductPrice;
import com.Ecommerce.demo.DTO.Product.Admin.AdminProductPriceRequest;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminCraudService extends BaseService {


    private ProductBasedCraud productBasedCraud;

    public ResponseEntity<?> deleteProduct(Long product_id) {
        return productBasedCraud.deleteProduct(product_id);
    }

    public ResponseEntity<?> bringBackProduct(Long product_id) {
        return productBasedCraud.bringBackProduct(product_id);
    }

    public ResponseEntity<?> editProductPriceVariant(Long productPrice_Id, AdminProductPriceRequest adminProductPrice) {
        return this.productBasedCraud.editProductPriceVariant(productPrice_Id, adminProductPrice);
    }

}
