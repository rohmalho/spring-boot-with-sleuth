package com.example.eShopping.shoppingcart.controller;

import com.example.eShopping.shoppingcart.service.ShoppingCartService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/v1/cart")
    public String submitShoppingCart(){
        log.info("Entered in the shopping cart post method");
        return shoppingCartService.submitShoppingCart();
    }
    @GetMapping("/v1/cart/{cartId}")
    public String getShoppingCart(@PathVariable String cartId){
        log.info("Entered in the shopping cart get method");
        return shoppingCartService.getShoppingCart(cartId);
    }
}
