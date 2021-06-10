/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.users;

import java.io.Serializable;

/**
 *
 * @author test
 */
public class UsersErrorDTO implements Serializable{
    private String userIDError;
    private String passwordError;
    private String confirm;
    private String phoneError;
    private String nameError;
    private String addressError;

    public UsersErrorDTO() {
    }

    public UsersErrorDTO(String userIDError, String passwordError, String phoneError, String nameError, String addressError,String confirm) {
        this.userIDError = userIDError;
        this.passwordError = passwordError;
        this.phoneError = phoneError;
        this.nameError = nameError;
        this.addressError = addressError;
        this.confirm=confirm;
    }

    /**
     * @return the userIDError
     */
    public String getUserIDError() {
        return userIDError;
    }

    /**
     * @param userIDError the userIDError to set
     */
    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    /**
     * @return the passwordError
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * @param passwordError the passwordError to set
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * @return the phoneError
     */
    public String getPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    /**
     * @return the nameError
     */
    public String getNameError() {
        return nameError;
    }

    /**
     * @param nameError the nameError to set
     */
    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    /**
     * @return the addressError
     */
    public String getAddressError() {
        return addressError;
    }

    /**
     * @param addressError the addressError to set
     */
    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    /**
     * @return the confirm
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "UsersErrorDTO{" + "userIDError=" + userIDError + ", passwordError=" + passwordError + ", confirm=" + confirm + ", phoneError=" + phoneError + ", nameError=" + nameError + ", addressError=" + addressError + '}';
    }
    
    
}
