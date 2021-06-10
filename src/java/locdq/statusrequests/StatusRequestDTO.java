/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.statusrequests;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class StatusRequestDTO implements Serializable{
    private String statusreqID;
    private String statusreqname;

    public StatusRequestDTO() {
    }

    public StatusRequestDTO(String statusreqID, String statusreqname) {
        this.statusreqID = statusreqID;
        this.statusreqname = statusreqname;
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
     * @return the statusreqname
     */
    public String getStatusreqname() {
        return statusreqname;
    }

    /**
     * @param statusreqname the statusreqname to set
     */
    public void setStatusreqname(String statusreqname) {
        this.statusreqname = statusreqname;
    }

    @Override
    public String toString() {
        return "StatusRequestDTO{" + "statusreqID=" + statusreqID + ", statusreqname=" + statusreqname + '}';
    }
        
}
