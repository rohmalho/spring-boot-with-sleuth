package com.example.eShopping.product.controller;

import com.example.eShopping.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/v1/product/{productId}")
    public String getProductDetails(@PathVariable String productId){
        log.info("Entered in the get the product controller method with id : {}", productId);
        return productService.getProductDetails(productId);
    }
}
