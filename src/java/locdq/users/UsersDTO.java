/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.users;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author test
 */
public class UsersDTO implements Serializable{
    private String userID;
    private String password;
    private String phone;
    private String name;
    private String address;
    private String statusID;
    private Date createdate;
    private String role;

    public UsersDTO() {
    }

    public UsersDTO(String userID, String password, String phone, String name, String address, String statusID, Date createdate, String role) {
        this.userID = userID;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.statusID = statusID;
        this.createdate = createdate;
        this.role = role;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the status
     */
    public String getStatusID() {
        return statusID;
    }

    /**
     * @param status the status to set
     */
    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the createdate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate the createdate to set
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UsersDTO{" + "userID=" + userID + ", password=" + password + ", phone=" + phone + ", name=" + name + ", address=" + address + ", statusID=" + statusID + ", createdate=" + createdate + ", role=" + role + '}';
    }

    
}
