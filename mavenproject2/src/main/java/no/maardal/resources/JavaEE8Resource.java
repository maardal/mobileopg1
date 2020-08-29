package no.maardal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("fant")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    @GET
    @Path("test")
    public Response test() {
        return Response
                .ok("test")
                .build();
    }
}
