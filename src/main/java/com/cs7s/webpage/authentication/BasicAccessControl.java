package com.cs7s.webpage.authentication;

import com.cs7s.webpage.database.StaffLoginDatabaseEngine;

/**
 * The implementation of AccessControl class.
 */
public class BasicAccessControl implements AccessControl {
	private StaffLoginDatabaseEngine staffLoginDBE = new StaffLoginDatabaseEngine();
	
	/**
	 * {@inheritDoc}
	 */
    @Override
    public boolean signIn(String username, String password) {
    	// Empty field check
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        
        // Check password
        try {
	        if (!this.staffLoginDBE.authenticate(username, password)) {
	        	return false;
	        }
        } catch (Exception e) {
        	System.out.println("Error ocurred: " + e.toString());
        	return false;
        }
        
        CurrentUser.set(username);
        return true;
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }
}
