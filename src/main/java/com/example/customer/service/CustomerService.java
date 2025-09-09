package com.example.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.entity.Customer;
import com.example.customer.handleexception.CustomerNotFoundException;
import com.example.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
    
	
	
	public Customer save(Customer c) {
		return repo.save(c);
		
	}
	
	public Optional<Customer> getCustomer(Long id) {
		return repo.findById(id);
	}
	
	public List<Customer> all(){
		return repo.findAll();
	}
	
	public void deleteCustomer(Long id) {
		if(!repo.existsById(id)) {
			throw new CustomerNotFoundException("Customer not found "+id);
		}
		
		repo.deleteById(id);
		
	}
	
	public Customer getEmail(String email) {
		return repo.findByEmail(email);
		
	}
	
	public Customer getEmailAndAddress(String email, String address) {
		return repo.findByEmailAndAddress(email, address);
		
	}
	
	
}
