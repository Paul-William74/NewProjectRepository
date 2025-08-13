package com.Ecommerce.demo.Controller.Product;

import com.Ecommerce.demo.Service.Product.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product to retrieve
     * @return ResponseEntity containing the product details
     */
    @PostMapping("/getProduct/{productId}")
    public ResponseEntity<?> getProduct(Long productId) {
        return aboutService.getProduct(productId);
    }

    /**
     * Retrieves additional details about a product by its ID.
     *
     * @param productId the ID of the product to retrieve additional details for
     * @return ResponseEntity containing the additional product details
     */
    @PostMapping("/getProductAdditionalDetails/{productId}")
    public ResponseEntity<?> getProductAdditionalDetails(@PathVariable Long productId) {
        return aboutService.getAdditionalDetails(productId);
    }


    @PostMapping("/getRecommendedProducts/{product_id}")
    public ResponseEntity<?> getRecommendedProducts(
            @PathVariable final Long product_id
    ) {
        return null;
    }


}
