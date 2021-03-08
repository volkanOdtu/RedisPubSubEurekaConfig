package com.volkan.accounts;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;

@Getter
public class Payment {

	 	private long id;
	    private long fromAccount;
	    private long toAccount;
	    private BigDecimal amount;
	    private Date date;
}
