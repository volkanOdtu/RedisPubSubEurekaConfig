package com.volkan.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	
	@Autowired
	private CustomerProperties customerProperties;
	
	@GetMapping("/{id}")
	public Customer findCustomer(@PathVariable("id") Long id) {
		return customerRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	@PostMapping
	public Customer createCustomer(@RequestBody Customer customer) {
		
		Customer newCustomer = customerRepository.save(customer);
		
		//String accountUrl = "http://localhost:8091/accounts/"; Eger @LoadBalanced kullanmasaydik boyle yazardik
		String accountUrl = customerProperties.getAccountsUrl();
		
		restTemplate.postForEntity(accountUrl
					, new Account(newCustomer.getId(), customerProperties.getInitAccountBalance()),
					Account.class );
					 
		return newCustomer;
	}
}
