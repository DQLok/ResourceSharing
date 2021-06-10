/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.resources;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author test
 */
public class ResourceDTO implements Serializable{
    private String itemID;
    private String itemname;
    private String color;
    private String categoryname;
    private int quantity;
    private Date dateresoure;

    public ResourceDTO() {
    }

    public ResourceDTO(String itemID, String itemname, String color, String categoryname, int quantity, Date dateresoure) {
        this.itemID = itemID;
        this.itemname = itemname;
        this.color = color;
        this.categoryname = categoryname;
        this.quantity = quantity;
        this.dateresoure=dateresoure;
    }

    

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the itemname
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * @param itemname the itemname to set
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the categoryname
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * @param categoryname the categoryname to set
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    /**
     * @return the dateresoure
     */
    public Date getDateresoure() {
        return dateresoure;
    }

    /**
     * @param dateresoure the dateresoure to set
     */
    public void setDateresoure(Date dateresoure) {
        this.dateresoure = dateresoure;
    }

    @Override
    public String toString() {
        return "ResourceDTO{" + "itemID=" + itemID + ", itemname=" + itemname + ", color=" + color + ", categoryname=" + categoryname + ", quantity=" + quantity + ", dateresoure=" + dateresoure + '}';
    }

    
}
