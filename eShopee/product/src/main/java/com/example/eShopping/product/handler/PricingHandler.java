package com.example.eShopping.product.handler;

import com.example.eShopping.product.config.CustomFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="pricing-service", configuration = CustomFeignConfig.class, url = "http://pricing-service:8082")
public interface PricingHandler {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/product/{productId}")
     String getPriceOfProduct(@RequestParam String productId);
}
