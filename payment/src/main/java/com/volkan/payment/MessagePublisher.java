package com.volkan.payment;

public interface MessagePublisher {
	void publish(Payment payment);
}
