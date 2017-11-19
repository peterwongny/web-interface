package com.cs7s.webpage.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

/**
 * The staff login entity that exists in the database.
 * @author Gian Miguel Sero Del Mundo
 */
@Entity // Refers to the type of record in a table.
@Table(name = "staff_login_table")
public class StaffLogin {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
}