package com.volkan.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisMessageSubscriber implements MessageListener {

	ObjectMapper ObjectMapper = new ObjectMapper();
	
	@Autowired
	AccountRepository accountRepository;

	//kafka'da @Listener kullaniliyodu ,Redis'de onMessage var 
	@Override
	public void onMessage(final Message message, byte[] pattern) {
		 try {
			 Payment payment = ObjectMapper.readValue( message.toString(), Payment.class);
			 Account fromAccount = accountRepository.findById(payment.getFromAccount() ).get();
			 Account toAccount = accountRepository.findById(payment.getToAccount()).get();
			 
			 fromAccount.setAmount(fromAccount.getAmount().subtract(payment.getAmount()));
			 toAccount.setAmount(toAccount.getAmount().add(payment.getAmount()));
			 accountRepository.save(fromAccount);
			 accountRepository.save(toAccount);
		 }catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Message received: " + new String(message.getBody()));
	}
}
