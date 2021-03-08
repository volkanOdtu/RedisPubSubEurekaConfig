package com.volkan.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class MessagingConfiguration {

	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}
	
	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("payments");
	}
	
	@Autowired
	RedisMessageSubscriber redisMessageSubscriber;
	
	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(redisMessageSubscriber);
	}
	@Bean
	RedisMessageListenerContainer listener() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListener(), topic());
		container.setRecoveryInterval(2000000);
		return container;
	}
}
