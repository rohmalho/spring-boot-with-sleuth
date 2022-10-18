package com.example.eShopping.product.service;

import com.example.eShopping.product.handler.PricingHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductService {

    @Autowired
    PricingHandler pricingHandler;

    public String getProductDetails(String productId){
        log.info("Entered in the get the product service method");
        log.info("Got the information about product :: {}", productId);
        return "Successfully Get Product "+productId+" with "+ getPriceInformation(productId);
    }

    private String getPriceInformation(String productId) {
        return pricingHandler.getPriceOfProduct(productId);
    }
}
