package com.volkan.payment;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@Configuration // required for injeciton
@ConfigurationProperties(prefix = "customers") //application.properties de customers da ilgili kisimlar var
public class CustomerProperties {
	 	private String accountsUrl;
	    private BigDecimal initAccountBalance;
	    private int maximumAccountsPerCustomer;
}
