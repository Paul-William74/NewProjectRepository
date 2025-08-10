package com.Ecommerce.demo.Mapper;

import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Product.WaitingList.WaitingWishListEntry;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.Product.ProductImage;
import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.WaitingList.WaitingList;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WaitingWishListMapper {

    @Autowired private Formatter formatter;


}
