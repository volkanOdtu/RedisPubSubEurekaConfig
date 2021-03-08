package com.volkan.accounts;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	private Long number;
	@NonNull
	private Long customerNumber;
	@NonNull
	private BigDecimal amount;
	
	public AccountDTO(Long number ,Long customerNumber ,BigDecimal amount) {
		this.number = number;
		this.customerNumber = customerNumber;
		this.amount = amount;
	}
	public AccountDTO() {}
}
