package no.maardal.fant.market;

import no.maardal.fant.market.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import no.maardal.fant.auth.Group;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

/**
 * REST service class to be used by the UI
 */
@Path("items")
public class FantService {
    

    /**
     * Public method that returns items with photos sold in the shop
     * @return 
     */
    @GET
    @Path("all")
    public List<Item> getItems() {
        return new ArrayList<>(); //only to be able to compile.
    }
    
    @GET
    @Path("/{itemId}")
    public Response getAnItem(@PathParam("itemId") long itemID) {
        return Response.ok("itemID " + itemID).build();
    }
    
    /**
     * A registered user may purchase an Item.An email will be sent to the
     * seller if the purchase is successful.
     * @param itemid unique id for item
     * @return resutlt of purchase request
     */
    @DELETE
    @Path("delete/{itemid}")
    public Response delete(@PathParam("itemid") long itemid) {
        return Response.ok("deleting").build(); //to avoid errors
    }
    
    
    /**
     * A registered user may add an item and photo(s) to Fant.
     * @param title the title of Item
     * @param description the description of Item
     * @param price the price of Item
     * @param photos one or more photos associated with Item
     * @return result of the request, if successful. The request will include
     *          the new unique ids of the Item and associated Photos
     */
    @POST
    @Path("add")
    @RolesAllowed(value = {Group.USER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(@FormParam("title") @NotEmpty String title,
                            @FormParam("desc") @NotEmpty String description,
                            @FormParam("price") @NotEmpty BigDecimal price,
                            FormDataMultiPart photos) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        return Response.ok().build();
    }
    
    /**
     * Streams an image to the browser (the actual compressed pixels). The image
     * will be scaled to the appropriate width if the width parameter is
     * provided. This is a public method available to all callers.
     * 
     * @param name the filename of the image
     * @param width the required scaled width of the image
     * 
     * @return the image in original format or in jpeg if scaled
     */
    
    @GET
    @Path("photo/{name}")
    public Response getPhoto(@PathParam("name") String name,
                             @QueryParam("width") int width) {
        return Response.ok().build();
    }
}
