package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The customer repository that performs query, insert, and update on Customer Table.
 * @author Wong Ngo Yin
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> 
{
//	List<Customer> findBytourJoined(String s);

}