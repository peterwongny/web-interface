package com.web.webpage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;


/**
 * booking (tour offering) repository that perform query, insert, update the database
 * @author Wong Ngo Yin
 *
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> 
{


}