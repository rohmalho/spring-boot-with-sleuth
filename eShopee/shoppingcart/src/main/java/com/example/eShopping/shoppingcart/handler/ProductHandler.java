package com.example.eShopping.shoppingcart.handler;

import com.example.eShopping.shoppingcart.config.CustomFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="product-service", configuration = CustomFeignConfig.class, url = "http://localhost:8081")
public interface ProductHandler {


    @RequestMapping(method = RequestMethod.GET,value = "/v1/product/{id1}")
    String getProductById(@PathVariable String id1);
}
