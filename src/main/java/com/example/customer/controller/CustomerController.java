package com.example.customer.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entity.Customer;
import com.example.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	public CustomerService service;
	

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Customer c) throws Exception, IllegalArgumentException {
		

		Customer cust = service.save(c);
		
		
		/*
		 * if (cust.getEmail() != null) { return
		 * ResponseEntity.status(HttpStatus.SC_CREATED).
		 * body("Customer Created Successfully");
		 * 
		 * } else { return
		 * ResponseEntity.status(HttpStatus.SC_CLIENT_ERROR).body("Customer Not Created"
		 * ); }
		 */
		if(cust.getEmail() == null) {
			logger.warn("Customer not created "+cust);
			throw new Exception("Customer email is required");
		}
		logger.info("Customer created successfully"+cust);
		return ResponseEntity.status(HttpStatus.SC_CREATED).body("Customer Created Successfully");

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<String> get(@PathVariable Long id) {
		Optional<Customer> cust = service.getCustomer(id);
		
		
		
		/*
		 * if (cust.isEmpty() != true) { return
		 * ResponseEntity.status(HttpStatus.SC_SUCCESS).
		 * body("Customer Details got Scucessfully"); } else { return
		 * ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).
		 * body("Customer Details are not found"); }
		 */	
		
		if (cust.isEmpty() == true) {
			logger.warn("Customer Details not retrived"+cust);
			throw new IllegalArgumentException();
		}
		logger.info("Customer Details got Scucessfully"+cust);
		return ResponseEntity.status(HttpStatus.SC_SUCCESS).body("Customer Details got Scucessfully");
	
  
	}

	@GetMapping("/all")
	public ResponseEntity<String> get() {
		List<Customer> cust = service.all();
		if (cust.isEmpty() != true) {
			return ResponseEntity.status(HttpStatus.SC_SUCCESS).body("Customers Details got Scucessfully");
		} else {
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Customers Details are not found");
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		service.deleteCustomer(id);
		return ResponseEntity.ok("Deleted");
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<String> getCustomerEmail(@PathVariable String email){
		Customer cust=service.getEmail(email);
		if(cust == null) {
			throw  new IllegalArgumentException();
		}
		return ResponseEntity.status(HttpStatus.SC_ACCEPTED).body(" Customer Email Found "+cust);
		
	}
	
	@GetMapping("/emailphone")
	public ResponseEntity<String> getCustomerEmailAndAddress(@RequestParam String email, @RequestParam String address ){
		Customer cust=service.getEmailAndAddress(email, address);
		if(cust == null) {
			throw  new IllegalArgumentException();
		}
		return ResponseEntity.status(HttpStatus.SC_ACCEPTED).body(" Customer Email and Phone details are Found "+cust);
		
	}

}
