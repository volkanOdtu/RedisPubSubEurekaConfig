package com.volkan.payment;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaymentRepository extends CrudRepository<Payment, Long> {
	@Query("select p from Payment p where p.fromAccount = ?1 or p.toAccount = ?1")
	List<Payment> getPaymentsForAccount(@Param("accountNumber") long accountNumber );
}
