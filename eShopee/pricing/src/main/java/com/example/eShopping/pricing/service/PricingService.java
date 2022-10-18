package com.example.eShopping.pricing.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PricingService {
    public String getPriceOfProduct(String productId) {
        log.info("Entered in the get the product service method");
        log.info("Got the information about product :: {}", productId);
        return "price of :: "+ 300.00;
    }
}
