package com.example.customer.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repo;

	@InjectMocks
	private CustomerService service;

	@Test
	public void addCustomerTest() {

		Customer cust = new Customer(1L, "Gandi", "Durgaprasad", "durga.gandi@example.com", "8497919691", "Hyderabad");
		when(repo.save(cust)).thenReturn(cust);

		Customer res = service.save(cust);

		assertNotNull(res);
		assertEquals("durga.gandi@example.com", res.getEmail());
		verify(repo).save(cust);

	}

	@Test
	public void getCustomerTest() {
		Customer cust = new Customer(1L, "Gandi", "Durgaprasad", "durga.gandi@example.com", "8497919691", "Hyderabad");
		when(repo.findById(1L)).thenReturn(Optional.of(cust));

		Optional<Customer> res = service.getCustomer(1L);
		assertNotNull(res);
		assertTrue(true);
		assertEquals("durga.gandi@example.com", res.get().getEmail());
	}
	
	
    // delete positive case
	@Test
	public void deleteCustomerTest() {
		Long id = 1L;
		when(repo.existsById(id)).thenReturn(true);

		service.deleteCustomer(id);

		verify(repo, times(1)).deleteById(id);

	}
	
	//delete negative case
	@Test
	public void deleteCustomerTest_NotFoundException() {
		Long id = 1L;
		when(repo.existsById(id)).thenReturn(false);
		
		RuntimeException exception=assertThrows(RuntimeException.class, () -> service.deleteCustomer(id));
		
		assertEquals("Product not found with id: 1",exception.getMessage());
		
	}
}
