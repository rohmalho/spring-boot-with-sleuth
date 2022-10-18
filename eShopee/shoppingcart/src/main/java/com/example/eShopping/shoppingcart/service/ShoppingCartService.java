package com.example.eShopping.shoppingcart.service;

import com.example.eShopping.shoppingcart.handler.ProductHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ShoppingCartService {

    @Autowired
    ProductHandler productHandler;
    public String submitShoppingCart(){
        log.info("Entered in the submit shopping service method");
        return "Submitted Successfully";
    }

    public String getShoppingCart(String cartId){
        log.info("Entered in the submit shopping service method with cartId :: {}", cartId);
        return productHandler.getProductById(cartId);
    }

}
