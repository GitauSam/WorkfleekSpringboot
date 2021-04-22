package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	 @Query(value = "DELETE FROM Customer c WHERE c.id = ?1")
	 int deleteCustomerByCustomerId(String customer_id);

	 @Modifying
	 @Query(value = "UPDATE Customer c SET c.firstName = ?1 WHERE c.id = ?2")
	 int updateCustomerByCustomerId(String firstName, String customer_id);

	 @Query(value = "SELECT c FROM Customer c WHERE c.email LIKE '%gmail%'")
	 List<Customer> findAllCustomersWhoseEmailContainsGmail();
}
