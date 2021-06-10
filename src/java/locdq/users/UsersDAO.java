/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.naming.NamingException;
import locdq.utils.DBUtils;


/**
 *
 * @author test
 */
public class UsersDAO implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public UsersDTO checklogin(String userID, String password) throws SQLException, NamingException {
        UsersDTO user = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, phone, name, address, statusID, createdate, role "
                        + "FROM tblUsers "
                        + "WHERE userID= ? AND password= ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userid = rs.getString("userID");
                    String passwordd = rs.getString("password");
                    String phone = rs.getString("phone");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String status = rs.getString("statusID");
                    Date createdate = rs.getDate("createdate");
                    String role = rs.getString("role");
                    user = new UsersDTO(userid, passwordd, phone, name, address, status, createdate, role);
                }
            }
        }finally {
            closeConnection();
        }
        return user;
    }

    public UsersDTO checkUserID(String userID) throws SQLException, NamingException {
        UsersDTO user = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT userID, password, phone, name, address, statusID, createdate, role "
                        + "FROM tblUsers "
                        + "WHERE userID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userid = rs.getString("userID");
                    String passwordd = rs.getString("password");
                    String phone = rs.getString("phone");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String status = rs.getString("statusID");
                    Date createdate = rs.getDate("createdate");
                    String role = rs.getString("role");
                    user=new UsersDTO(userid, passwordd, phone, name, address, status, createdate, role);
                }

            }
        }finally {
            closeConnection();
        }
        return user;
    }

    public boolean insert(UsersDTO user) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblUsers(userID,password,phone,name,address,statusID,createdate,role) "
                        + "VALUES (?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getPhone());
                stm.setString(4, user.getName());
                stm.setString(5, user.getAddress());
                stm.setString(6, user.getStatusID());
                stm.setDate(7, user.getCreatedate());
                stm.setString(8, user.getRole());
                check = stm.executeUpdate() == 0 ? false : true;
            }
        }finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean AcceptUser(String userID) throws SQLException, NamingException{
        boolean check=false;
        try{
            con=DBUtils.makeConnection();
            if (con!=null){
                String sql="UPDATE tblUsers SET statusID = ? "
                        + "WHERE userID = ? ";
                stm=con.prepareStatement(sql);
                stm.setString(1, "s2");
                stm.setString(2, userID);
                check=stm.executeUpdate()==0?false:true;
            }
        }finally{
            closeConnection();
        }
        return check;
    }
}
