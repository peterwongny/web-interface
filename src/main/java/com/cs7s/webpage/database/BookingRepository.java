package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The booking (tour offering) repository that performs query, insert, and update on Booking Table.
 * @author Wong Ngo Yin
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> 
{
	List<Booking> findByBookingId(String s);

}