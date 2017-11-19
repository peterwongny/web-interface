package com.cs7s.webpage.database;

import java.sql.*;

/**
 * The database engine for the Staff Login Table.
 */
public class StaffLoginDatabaseEngine extends DatabaseEngine {
	/**
	 * Returns true if the user can be authenticated with the given username and password.
	 * @param username the username.
	 * @param password the password.
	 * @return true if the user can be authenticated with the given username and password.
	 * @throws Exception any exception that occurs with the connection to the database.
	 */
	public boolean authenticate(String username, String password) throws Exception {
		try {
			Connection connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT EXISTS(SELECT * FROM staff_login_table "
					+ "WHERE username=LOWER(?) AND password=crypt(?, password))");
			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			rs.next();
			boolean result = rs.getString(1).equals("t") ? true : false;
			System.out.println("Result = " + rs.getString(1));
			System.out.println("So return " + result);
			
			rs.close();
			stmt.close();
			connection.close();

			return result;
		} catch (Exception e) {
			System.out.println("Exception occured: " + e.toString());
			throw new Exception(e.toString());
		}
	}
}