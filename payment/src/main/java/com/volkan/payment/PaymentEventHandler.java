package com.volkan.payment;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

//Bunu restController gibi dusunebiliriz ,Spring in onceki versiyonlarinda create boyle yapliyormus
@Component  
@RepositoryEventHandler(Payment.class)
public class PaymentEventHandler {
	@Autowired
	private RedisMessagePublisher publisher;
	
	//Payment create sonrasi, bu metod cagriliyor
	@HandleAfterCreate
	public void handlePaymentSave(Payment payment) {
		publisher.publish(payment);
	}
}
