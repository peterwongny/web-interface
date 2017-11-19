package com.cs7s.webpage.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The staff login repository to authenticate the person logging in.
 * @author Gian Miguel Sero Del Mundo
 */
@Repository
public interface StaffLoginRepository extends JpaRepository<StaffLogin, Long> {
	/**
	 * @param username the username of the person signing in.
	 * @param password the password of the person signing in.
	 * @return "t" if the credentials are correct, otherwise "f".
	 */
	@Query(value = "SELECT EXISTS(SELECT * FROM staff_login_table "
			+ "WHERE username=LOWER(?1) AND password=crypt(?2, password))",
		    nativeQuery = true)
	public String authenticate(String username, String password);
}
