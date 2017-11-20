package com.cs7s.webpage.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Base class to establish a connection with the database.
 */
public class DatabaseEngine {
	/**
	 * Establishes a connection with the database.
	 * @return the database connection.
	 * @throws URISyntaxException
	 * @throws SQLException
	 */
    protected static Connection getConnection() throws URISyntaxException, SQLException {
        Connection connection;
        URI dbUri = new URI("postgres://vteanzmjlsvowk:0be547741a25775b429868e9ba17f4b6ce86363a16efa0d4dd124d01615f5e89@ec2-54-221-241-23.compute-1.amazonaws.com:5432/da5ls83lij16cs");

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        
        connection = DriverManager.getConnection(dbUrl, username, password);

        return connection;
    }
}
