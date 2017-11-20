package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


/**
 * The customer repository that performs query, insert, and update on Customer Table.
 * @author Wong Ngo Yin
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> 
{
	@Query(value = "SELECT * FROM customer_table WHERE tour_joined = ?",
		    nativeQuery = true)
	List<Customer> findByTourJoined(String s);
}