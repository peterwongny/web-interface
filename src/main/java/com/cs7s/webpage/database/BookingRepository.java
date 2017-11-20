package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


/**
 * The booking (tour offering) repository that performs query, insert, and update on Booking Table.
 * @author Wong Ngo Yin
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> 
{

	@Query(value = "SELECT * FROM booking_table WHERE booking_id = ?",
		    nativeQuery = true)
	Booking findById(String id);

}