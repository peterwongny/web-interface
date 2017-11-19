package com.cs7s.webpage.authentication;

/**
 * The access controller that handles user sign in.
 */
public interface AccessControl {
	/**
	 * Handles signing in of a user.
	 * @param username the username.
	 * @param password the password.
	 * @return true if the user successfully signed in.
	 */
    public boolean signIn(String username, String password);

    /**
     * Returns true if there is a user that is signed in.
     * @return true if there is a user that is signed in.
     */
    public boolean isUserSignedIn();
}