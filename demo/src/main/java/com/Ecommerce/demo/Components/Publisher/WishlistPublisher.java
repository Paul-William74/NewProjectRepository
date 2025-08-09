package com.Ecommerce.demo.Components.Publisher;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.Events.Customer.WishListItemOnSpecialEvent;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.Product.ProductVariant;
import com.Ecommerce.demo.Model.User.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishlistPublisher extends Publisher {

    private final Formatter formatter;

    @Async
    public void publishWishlistProductOnSale(Customer customer, ProductVariant productVariant, MATERIAL material) {
        applicationEventPublisher.publishEvent(new WishListItemOnSpecialEvent(
                this, emailSender, customer, productVariant , material, formatter)
        );
    }

}
