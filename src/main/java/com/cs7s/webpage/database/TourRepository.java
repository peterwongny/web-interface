package com.cs7s.webpage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * tour repository that perform query, insert, update the database tour table
 * @author Wong Ngo Yin
 *
 */
@Repository
public interface TourRepository extends JpaRepository<Tour, Long> 
{
	//List<Tour> findAll()
	//List<Tour> saveAll(Iterable<Tour>)
	//Tour save(Tour) 
	//are already implemented by default
	List<Tour> findByDescriptionContainingIgnoreCase(String name);
	
	@Query(value = "SELECT * FROM tour_list WHERE LOWER(?1) LIKE "
			+ "CONCAT('%', LOWER(name), '%') "
			+ "UNION "
			+ "SELECT * FROM tour_list WHERE LOWER(name) LIKE "
			+ "CONCAT('%', LOWER(?1), '%')",
		    nativeQuery = true)
	List<Tour> findByName(String query);
	
	
	List<Tour> findById(String id);

}