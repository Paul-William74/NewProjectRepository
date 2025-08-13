package com.Ecommerce.demo.Service.User.Admin;


import com.Ecommerce.demo.Components.Publisher.WishlistPublisher;
import com.Ecommerce.demo.Repository.Product.ProductPriceRepo;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AdminService extends BaseService {

    private final WishlistPublisher wishlistPublisher;
    private final ProductPriceRepo productPriceRepo;


}
