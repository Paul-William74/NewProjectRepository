package com.Ecommerce.demo.Service;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareService extends BaseService {

    private final Formatter formatter;


    public ResponseEntity<?> addToCompare(Long customer_id, Long product_id) {

        Customer customer = this.findCustomer(customer_id);
        Product product = this.findProduct(product_id);

        ProductsCompare productsCompare = new ProductsCompare();
        productsCompare.setProduct(product); //reference the product

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
}
