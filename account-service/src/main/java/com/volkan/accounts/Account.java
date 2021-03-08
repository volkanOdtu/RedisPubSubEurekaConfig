package com.volkan.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue
	private Long number;
	private Long customerNumber;
	private BigDecimal amount;
	
	public Account(BigDecimal amount) {
		this.amount = amount;
	}
	public AccountDTO toDTO() {
		AccountDTO dto = new AccountDTO(number ,customerNumber ,amount);
		return dto;
	}
	
	
	
}
