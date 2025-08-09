package com.Ecommerce.demo.Service.Cart;


import com.Ecommerce.demo.Components.Formatter;
import com.Ecommerce.demo.DTO.Cart.CartProduct;
import com.Ecommerce.demo.DTO.Cart.CartProductsContainer;
import com.Ecommerce.demo.Mapper.CartMapper;
import com.Ecommerce.demo.Model.Cart.CART_STATUS;
import com.Ecommerce.demo.Model.Cart.Cart;
import com.Ecommerce.demo.Model.User.Customer;
import com.Ecommerce.demo.Service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService extends BaseService {


    private final CartMapper cartMapper;
    private final Formatter formatter;

    public ResponseEntity<?> getCartItems(Long customerId) {

        Customer customer = this.findCustomer(customerId); //find the customer by ID
        Cart cart = this.findCustomerCart(customer, CART_STATUS.ACTIVE); //find that customer's active cart


        List<CartProduct> cartProducts = this.cartMapper.toCartProducts(cart.getCartItemList()); //map the cart items to CartProduct DTOs

        //if the cart is empty, return an empty CartProductsContainer to avoid using more resources
        if( cartProducts.isEmpty())
            return ResponseEntity.ok(new CartProductsContainer(0,
                    new LinkedList<>(), "R0.00"));


        String formattedPrice = formatter.getFormattedPrice(cart.getTotal()); //get the total price of the cart and format it
        CartProductsContainer cartProductsContainer = new CartProductsContainer(
                cart.getCartItemList().size(), //total number of items in the cart
                cartProducts, //list of CartProduct DTOs
                formattedPrice //total price of the cart
        );

        return ResponseEntity.ok(cartProductsContainer); //return the CartProductsContainer
    }
}
