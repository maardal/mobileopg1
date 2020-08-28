/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no.maardal.fant;

/**
 * An item to be sold in the Fant webstore
 * @author Martin
 */
public class Item {
    
    private long id;
    
    private String title;
    private String description;
    private String category;

    private User seller;

    public Item() {
    }

    public Item(long id, String title, String description, String category, User seller) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.seller = seller;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
    
    
}
