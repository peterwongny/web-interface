package com.cs7s.webpage.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Staff login repository 
 * @author Gian Miguel Sero Del Mundo
 *
 */
@Repository
public interface StaffLoginRepository extends JpaRepository<StaffLogin, Long> {
	@Query(value = "SELECT EXISTS(SELECT * FROM staff_login_table "
			+ "WHERE username=LOWER(?1) AND password=crypt(?2, password))",
		    nativeQuery = true)
	String authenticate(String username, String password);
}
