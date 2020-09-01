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
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

/**
 * REST service class to be used by the UI
 * @author Martin
 */
public class FantService {
    /**
     * Public method that returns items with photos sold in the shop
     * @return 
     */
    public List<Item> getItems() {
        return new ArrayList<>(); //to avoid errors
    }
    
    /**
     * A registered user may purchase an Item.An email will be sent to the
     * seller if the purchase is successful.
     * @param itemid unique id for item
     * @return resutlt of purchase request
     */
    public Response delete(long itemid) {
        return Response.ok().build(); //to avoid errors
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
    public Response addItem(String title, String description, BigDecimal price,
            FormDataMultiPart photos) {
        return Response.ok().build(); 
        //NB Response should be 201 -> .created(location)
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
    public Response getPhoto(String name, int width) {
        return Response.ok().build();
    }
}
