package com.Ecommerce.demo.Events.Customer;

import com.Ecommerce.demo.Events.EmailSendingService;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import org.springframework.context.ApplicationEvent;

public class BackInStockEvent extends ApplicationEvent implements EmailSendingService {

    private final ProductSize productSize;
    private final Customer customer;

    public BackInStockEvent(Object source, ProductSize productSize, Customer customer) {
        super(source);
        this.productSize = productSize;
        this.customer = customer;
    }


    @Override
    public void sendEmail() {
        // Logic to send an email notification about the product being back in stock
    }
}
