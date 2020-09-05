package no.maardal.fant;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import no.maardal.fant.entities.User;
import javax.ws.rs.core.Response;

/**
 * REST service class used for authentication
 * @author Martin
 */
@Path("auth")
public class AuthenticationService {
    
    @GET
    public Response alive() {
        return Response.ok("alive").build();
    }
    
    /**
     * Authenticates user based on userid and password.
     * 
     * @param userid the userid of the user
     * @param password the password of the user
     * 
     * @return result of the request.
     */
    @POST
    @Path("login")
    public Response login(@FormParam("userid") String userid,
                          @FormParam("password") String password) {
        return Response.ok("login").build();
    }
    
    /**
     * Creates a new user
     * 
     * @param userid
     * @param password
     * @return 
     */
    @POST
    @Path("createUser")
    public Response createUser(@FormParam("userid") String userid,
                               @FormParam("password") String password) {
        return Response.ok("create").build();
    }
     
    /**
     * 
     * @return 
     */
    @GET
    @Path("currentUser")
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
    @POST
    @Path("changePassword")
    public Response changePassword(@FormParam("userid") String userid,
                                   @FormParam("password") String password) {
        return Response.ok("change").build();
    }
    
}
