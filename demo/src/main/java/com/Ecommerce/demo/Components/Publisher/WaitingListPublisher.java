package com.Ecommerce.demo.Components.Publisher;

import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitingListPublisher extends Publisher {


    @Async
    public void publishWaitingListEntryAdded(Customer customer, ProductSize productSize) {
        applicationEventPublisher.publishEvent(null);
    }

    @Async
    public void publishRestockedProductsFromWaitingList(Customer customer, ProductSize productSize) {}

}
