package com.web.webpage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface BookingRepository extends JpaRepository<Tour, Long> 
{
	//Iterable<Tour> findAll()
	//Iterable<Tour> saveAll(Iterable<Tour>)
	//Tour save(Tour) 
	//are already implemented by default
	List<Tour> findByDescriptionContainingIgnoreCase(String name);
	

}