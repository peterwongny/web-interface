package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * customer repository that perform query, insert, update the database
 * @author Wong Ngo Yin
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> 
{
	

}
