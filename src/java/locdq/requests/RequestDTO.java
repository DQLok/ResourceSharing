/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.requests;

import java.io.Serializable;
import java.sql.Date;
import locdq.resources.ResourceDTO;

/**
 *
 * @author test
 */
public class RequestDTO implements Serializable{
    private int requestID;
    private Date datebook;
    private int quantityReq;
    private String statusreqID;
    private String userID;
    private ResourceDTO item;

    public RequestDTO() {
    }

    public RequestDTO(int requestID, Date datebook, int quantityReq, String statusreqID, String userID, ResourceDTO item) {
        this.requestID = requestID;
        this.datebook = datebook;
        this.quantityReq = quantityReq;
        this.statusreqID = statusreqID;
        this.userID = userID;
        this.item = item;
    }

    

    

    /**
     * @return the datebook
     */
    public Date getDatebook() {
        return datebook;
    }

    /**
     * @param datebook the datebook to set
     */
    public void setDatebook(Date datebook) {
        this.datebook = datebook;
    }

    /**
     * @return the statusreqID
     */
    public String getStatusreqID() {
        return statusreqID;
    }

    /**
     * @param statusreqID the statusreqID to set
     */
    public void setStatusreqID(String statusreqID) {
        this.statusreqID = statusreqID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the quantityReq
     */
    public int getQuantityReq() {
        return quantityReq;
    }

    /**
     * @param quantityReq the quantityReq to set
     */
    public void setQuantityReq(int quantityReq) {
        this.quantityReq = quantityReq;
    }

    /**
     * @return the requestID
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     * @return the item
     */
    public ResourceDTO getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ResourceDTO item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "RequestDTO{" + "requestID=" + requestID + ", datebook=" + datebook + ", quantityReq=" + quantityReq + ", statusreqID=" + statusreqID + ", userID=" + userID + ", item=" + item + '}';
    }

    
    
       
}
