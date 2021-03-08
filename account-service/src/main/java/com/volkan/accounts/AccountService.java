package com.volkan.accounts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	public List<Account> findAll(){
		return accountRepository.findAll();
	}
	
	public List<Account> findAllById(Iterable<Long> iterable){
		return accountRepository.findAllById(iterable);
	}
	
	public List<Account> saveAll(Iterable<Account> iterable){
		return accountRepository.saveAll(iterable);				
	}
	
	public void flush() {
		accountRepository.flush();
    }

    public Account saveAndFlush(Account s) {
        return accountRepository.saveAndFlush(s);
    }

    public void deleteInBatch(Iterable<Account> iterable) {
    	accountRepository.deleteInBatch(iterable);
    }

    public void deleteAllInBatch() {
    	accountRepository.deleteAllInBatch();
    }

    public Account save(Account s) {
        return accountRepository.save(s);
    }

    public Account getOne(Long aLong) {
        return accountRepository.getOne(aLong);
    }

    public Optional<Account> findById(Long aLong) {
        return accountRepository.findById(aLong);
    }

    public Optional<List<Account>> findByCustomerNumber(@Param("customerNumber") long customerNumber) {
        return accountRepository.findByCustomerNumber(customerNumber);
    }
}
