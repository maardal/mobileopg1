/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.maardal.fant;

import no.maardal.fant.entities.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * REST service class to be used by the UI
 */
@Path("items")
public class FantService {
    
    @GET
    public Response test() {
        return Response.ok("test").build();
    }
    /**
     * Public method that returns items with photos sold in the shop
     * @return 
     */
    @GET
    
    public List<Item> getItems() {
        return new ArrayList<>(); //to avoid errors
    }
    
    @GET
    @Path("/{itemId}")
    public Response getAnItem(@PathParam("itemId") long itemID) {
        return Response.ok("tulla " + itemID).build();
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
    public Response addItem(@FormParam("title") String title,
                            @FormParam("desc") String description,
                            @FormParam("price") BigDecimal price,
                            @FormDataParam("photos") FormDataMultiPart photos,
                            @Context UriInfo uriInfo) {
        Item item = new Item(title, description, price);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(item.getItemId()));
        return Response.created(builder.build()).build();
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
    @Path("getPhoto")
    public Response getPhoto(@QueryParam("name") String name,
                             @QueryParam("widht") int width) {
        return Response.ok().build();
    }
}
