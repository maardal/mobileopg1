/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.maardal.fant;

import javax.ws.rs.core.Response;

/**
 * REST service class used for authentication
 * @author Martin
 */
public class AuthenticationService {
    
    /**
     * Authenticates user based on userid and password.
     * 
     * @param userid the userid of the user
     * @param password the password of the user
     * 
     * @return result of the request.
     */
    public Response login(String userid, String password) {
        return Response.ok().build();
    }
    
    /**
     * Creates a new user
     * 
     * @param userid
     * @param password
     * @return 
     */
    public Response createUser(String userid, String password) {
        return Response.ok().build();
    }
     
    /**
     * 
     * @return 
     */
    public User getCurrentUser() {
        return new User();
    }
    
    /**
     * Change password of current user if current user has the role of
     * administrator.
     * @param userid
     * @param password
     * @return 
     */
    public Response changePassword(String userid, String password) {
        return Response.ok().build();
    }
    
}
