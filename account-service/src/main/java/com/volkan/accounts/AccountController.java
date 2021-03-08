package com.volkan.accounts;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private  AccountService accountService;
	
	@PostMapping
	public AccountDTO createAccount(@RequestBody Account account) {
		 return accountService.save(account).toDTO();
	}
	
	@GetMapping("/{id}")
	public AccountDTO getAccount(@PathVariable("id") Long id) {
		return accountService.findById(id)
				.map(a ->a.toDTO())
				.orElseThrow(() ->new RuntimeException("Not Found"));
	}
	
	@GetMapping
	public List<AccountDTO> getAccounts(@RequestParam(value ="customerNumber" ,required = false ) Long customerNumber){
		
		if(customerNumber == null)
			return accountService.findAll().stream().map(a ->a.toDTO()).collect(Collectors.toList() );
		
		if( accountService.findByCustomerNumber(customerNumber).isPresent()) {
					return accountService.findAll().stream().map(a ->a.toDTO()).collect(Collectors.toList() );				
		}else
			throw new RuntimeException("Not found");
		
	}
}
