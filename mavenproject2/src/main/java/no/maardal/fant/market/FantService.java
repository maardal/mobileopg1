package no.maardal.fant.market;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
import javax.ws.rs.core.Response.Status;
import lombok.extern.java.Log;
import no.maardal.fant.auth.Group;
import no.maardal.fant.market.Item;

/**
 * REST service class to be used by the UI
 */
@Path("items")
@Stateless
@Log
public class FantService {
       
    @PersistenceContext
    EntityManager em;

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
                            @FormParam("price") @NotNull BigDecimal price/*,
                            FormDataMultiPart photos*/) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        return Response.ok(em.merge(item)).build();
    }
    
    @POST
    @Path("remove")
    @RolesAllowed(value = {Group.USER})
    public Response removeItem(@FormParam("itemid") @NotEmpty String itemID) {
        Item item = (Item) em.createNamedQuery(Item.FIND_ITEM_BY_IDS, Item.class);
//em.find(Item.class, itemID);
        if (item == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        em.remove(item);
        return Response.ok().build();
    }
    
    /**
     * Public method that returns all items with photos for sale in the shop
     * @return 
     */
    @GET
    @Path("all")
    public List<Item> getItems() {
        return em
                .createNamedQuery(Item.FIND_ALL_ITEMS, Item.class)
                .getResultList(); 
    }
    
    /**
     * Return specific item for sale in the 
     * @param itemID
     * @return 
     */
    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnItem(@PathParam("itemId") String itemID) {
        Item item = findItem(itemID);
        return Response.ok(item).build();
    }
    
    private Item findItem(String itemID) {
        return em.createNamedQuery(Item.FIND_ITEM_BY_IDS, Item.class)
                 .setParameter("itemid", itemID)
                 .getSingleResult();
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
