/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.categorys;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class CategoryDTO implements Serializable{
    private String categoryID;
    private String categoryname;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryID, String categoryname) {
        this.categoryID = categoryID;
        this.categoryname = categoryname;
    }

    /**
     * @return the categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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

    @Override
    public String toString() {
        return "CategoryDTO{" + "categoryID=" + categoryID + ", categoryname=" + categoryname + '}';
    }
    
    
}
