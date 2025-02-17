package com.volkan.accounts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<List<Account>> findByCustomerNumber(@Param("customerNumber") long customerNumber);
}
