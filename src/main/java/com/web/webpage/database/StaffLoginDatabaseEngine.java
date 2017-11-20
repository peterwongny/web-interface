package com.web.webpage.database;
import java.sql.*;

/**
 * The database engine to connect to Staff Login Table.
 */
public class StaffLoginDatabaseEngine extends DatabaseEngine {
	/**
	 * Authenticates the input credentials.
	 * @param username the username.
	 * @param password the password.
	 * @return true if the credentials were successfully authenticated.
	 * @throws Exception
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