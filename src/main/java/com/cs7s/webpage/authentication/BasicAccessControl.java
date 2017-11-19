package com.cs7s.webpage.authentication;

import com.cs7s.webpage.database.StaffLoginRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * The implementation of AccessControl class.
 */
@SpringComponent
@UIScope
public class BasicAccessControl implements AccessControl {
	private StaffLoginRepository staffLoginRepo;
	
	public BasicAccessControl(StaffLoginRepository staffLoginRepo) {
		this.staffLoginRepo = staffLoginRepo;
	}
	
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
	        if (this.staffLoginRepo.authenticate(username, password).equals("false")) {
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
