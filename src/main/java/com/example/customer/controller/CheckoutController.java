
package com.example.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.service.CheckoutService;

@RestController
@RequestMapping("/api/customers")
public class CheckoutController {
	
	@Autowired
	private CheckoutService service;
	
	@PostMapping("/checkout")
	
	public Object checkout(@RequestBody CheckoutRequest req) {
		return service.checkout(req);
	}

}
