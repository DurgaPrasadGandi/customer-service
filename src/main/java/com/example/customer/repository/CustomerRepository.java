package com.example.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.customer.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	public Customer findByEmail(String email);
	
	public Customer findByEmailAndAddress(String email, String address);

}
