package com.volkan.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher implements MessagePublisher {

	@Autowired
	private RedisTemplate<String, Payment> redisTemplate;
	
	@Autowired
	private ChannelTopic topic;
	
	 
	public void publish(Payment paymentMessage) {
		redisTemplate.convertAndSend(topic.getTopic(), paymentMessage);
	}

}
