package com.example.eShopping.pricing.controller;

import com.example.eShopping.pricing.service.PricingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class PricingController {

    @Autowired
    PricingService pricingService;

    @GetMapping("/v1/product/{productId}")
    public String getProductDetails(@PathVariable String productId){
        log.info("Entered in the get the pricing controller method with product id : {}", productId);
        return pricingService.getPriceOfProduct(productId);
    }
}
