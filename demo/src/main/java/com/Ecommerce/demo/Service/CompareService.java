package com.Ecommerce.demo.Service;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.Compare.ProductCompareDTO;
import com.Ecommerce.demo.Mapper.ProductMapper;
import com.Ecommerce.demo.Mapper.ProductPriceMapper;
import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompareService extends BaseService {

    private final Formatter formatter;

    private final ProductPriceMapper productPriceMapper;
    private final ProductMapper productMapper;

    public ResponseEntity<?> addToCompare(Long customer_id, Long product_id) {

        Customer customer = this.findCustomer(customer_id);
        Product product = this.findProduct(product_id);

        ProductsCompare productsCompare = new ProductsCompare(customer, product);

        try  {
            customer.addProductCompare(productsCompare);
            this.userRepo.save(customer);
            return ResponseEntity.ok(" successfully added we need to return the object added for comparison");
        }catch (DataIntegrityViolationException exception) {
            return ResponseEntity.badRequest()
                    .body(product.getName() + " is already chosen to be compared against");
        }

    }

    public ResponseEntity<?> removeCompare(Long customer_id, Long product_id) {

        Customer customer = this.findCustomer(customer_id);
        Product product = this.findProduct(product_id);
        ProductsCompare productsCompare = this.findProductsCompare(customer, product);

        customer.getProductsCompares().remove(productsCompare);
        this.userRepo.save(customer);

        //return the product back for removal
        return ResponseEntity.ok(product_id);
    }


    public ResponseEntity<?> getComparedProducts(Long customer_id) {

        Customer customer = findCustomer(customer_id);
        List<ProductsCompare> productsCompares = customer.getProductsCompares();

        List<ProductCompareDTO> productCompareDTOS = this.productPriceMapper.productCompareDTOs(null, null, null);
        return ResponseEntity.ok("");
    }
}
