package com.example.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.customer.controller.CheckoutRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
@RefreshScope
public class CheckoutService {
	@Autowired
	@Lazy
	private RestTemplate template;
	
	@Value("${microservice.product-service.endpoint.uri}")
	private String url;
	
	
	@CircuitBreaker(name="product-service", fallbackMethod="getProductDetailsFallBack")
	public String checkout(CheckoutRequest req) {
		String produrl = "http://localhost:8080/api/products/sku/" + req.getSku();
		ResponseEntity<String> prod = template.getForEntity(produrl, String.class);
		if (prod != null) {
			return prod.getBody();
		} else {
			throw new IllegalArgumentException("Product not found");
		}
	}
	
	public String getProductDetailsFallBack(Exception ee) {
		return "This product is not available";
	}
}
