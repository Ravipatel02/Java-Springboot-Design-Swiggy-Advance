package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT c FROM Customer c WHERE c.username = :username")
	Customer findByUsername(@Param("username") String username);
	
//	 @Query("SELECT c FROM Customer c WHERE c.email = :email")
//	    Customer findByEmailCustomQuery(@Param("email") String email);

}
