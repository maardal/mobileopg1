package no.maardal.fant;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import no.maardal.fant.entities.User;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
    //@Consumes("multipart/form-data")
    //@Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormDataParam("userid") String userid,
                          @FormDataParam("password") String password) {
        return Response.status(Response.Status.ACCEPTED).build();
    }
    
    /**
     * Creates a new user
     * 
     * @param userid
     * @param password
     * @return 
     */
    @POST
    @Path("createuser")
    @Consumes()
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
    @Produces(MediaType.APPLICATION_JSON)
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
    @Path("changepassword")
    public Response changePassword(@FormParam("userid") String userid,
                                   @FormParam("password") String password) {
        return Response.ok("change").build();
    }
    
}
