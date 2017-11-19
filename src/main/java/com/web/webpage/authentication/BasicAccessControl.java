package com.web.webpage.authentication;

import com.web.webpage.database.StaffLoginDatabaseEngine;

public class BasicAccessControl implements AccessControl {

	private StaffLoginDatabaseEngine staffLoginDBE = new StaffLoginDatabaseEngine();
	
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

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public boolean isUserInRole(String role) {
        if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

}
