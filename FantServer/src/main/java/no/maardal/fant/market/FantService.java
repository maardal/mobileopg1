package no.maardal.fant.market;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
import no.maardal.fant.auth.AuthenticationService;
import no.maardal.fant.auth.Group;

/**
 * REST service class to be used by the UI
 */
@Path("items")
@Stateless
@Log
public class FantService {
    
    @Inject
    AuthenticationService as;
    
    @Inject
    MailService ms;
    
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
        item.setSeller(as.getCurrentUser());
        return Response.ok(em.merge(item)).build();
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
        if(item == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(item).build();
    }
       
    /**
     * A registered user may purchase an Item.An email will be sent to the
     * seller if the purchase is successful.
     * @param itemid unique id for item
     * @return resutlt of purchase request
     */
    public Response purchase(String itemid) {
        return Response.ok().build();
    }
    
    
    /**
     * A registered user may remove an item and associated photos owned by the
     * calling user. An user with administrator privileges remove any item
     * and associated photos
     * @param itemid unique id for item to be deleted
     * @return result of delte request
     */
    @DELETE
    @Path("delete")
    @RolesAllowed(value = {Group.USER})
    public Response delete(@QueryParam("itemid") @NotEmpty String itemID) {
        Item item = findItem(itemID);
        if (item == null) {
            log.log(Level.FINE, "findItem called on non-existing Item ID");
            return Response.status(Status.NOT_FOUND).build();
        }
        if (!as.getCurrentUser().getUserid().equals(item.getSeller().getUserid())) {
            log.log(Level.WARNING, "Delete Item: Unathorized user ");
            return Response.status(Status.UNAUTHORIZED).build();
        }
        em.remove(item);
        log.log(Level.INFO, "Item with ID " + itemID + " removed");
        return Response.status(Status.NO_CONTENT).build();
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
    
    /**
     * 
     * @param itemID 
     * @return Item item wih matching itemID, null if not.
     */
    private Item findItem(String itemID) {
    Item item;
    try {
        item = em.createNamedQuery(Item.FIND_ITEM_BY_ID, Item.class)
                 .setParameter("itemid", itemID)
                 .getSingleResult();
    } catch (NoResultException e) {
        log.log(Level.FINE, "findItem called on non-existing Item ID", e);
        item = null;
    }
    return item;
    }
}
