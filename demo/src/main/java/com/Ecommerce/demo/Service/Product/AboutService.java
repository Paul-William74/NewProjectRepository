package com.Ecommerce.demo.Service.Product;


import com.Ecommerce.demo.DTO.Product.About.AboutProduct;
import com.Ecommerce.demo.DTO.Product.About.ProductAdditionalInformation;
import com.Ecommerce.demo.Mapper.ProductMapper;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutService extends BaseService {

    private final ProductMapper productMapper;

    /**
     * gets a product along with all its details
     * @param productId product ID to retrieve the product and its details
     * @return ResponseEntity containing the AboutProduct___ DTO with all the product details
     */
    public ResponseEntity<?> getProduct(Long productId) {

        Product product = this.findProduct(productId); //get the product by ID
        AboutProduct aboutProduct = this.productMapper.toAboutProduct(product); //map the product to AboutProduct___ DTO

        double totalProductRating = this.reviewRepo.findAverageRatingByProductId(productId); //get the average rating of that product
        aboutProduct.setTotalRating(totalProductRating); //set the total rating of that product
        return ResponseEntity.ok(aboutProduct);
    }


    /**
     * Retrieves additional details about a product by its ID.
     * @param productId the ID of the product to retrieve additional details for
     * @return ResponseEntity containing the ProductAdditionalInformation DTO with additional product details
     */
    public ResponseEntity<?> getAdditionalDetails(Long productId) {
        Product product = this.findProduct(productId); //get the product by ID

        //map the product to ProductAdditionalInformation DTO
        ProductAdditionalInformation productAdditionalInf = this.productMapper.toProductAdditionalInformation(product);
        return ResponseEntity.ok(productAdditionalInf);
    }

}
