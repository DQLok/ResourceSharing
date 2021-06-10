/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.requests;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locdq.resources.ResourceDTO;
import locdq.utils.DBUtils;

/**
 *
 * @author test
 */
public class RequestDAO implements Serializable {

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

    public int countRequest(String search, String status, String date, String user) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT(r.requestID) as count "
                        + " FROM tblRequest r,tblStatusRequest sr,tblResource res "
                        + " WHERE r.statusreqID=sr.statusreqID AND r.itemID=res.itemID " + status + date + user + search;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<RequestDTO> getListRequest(String search, String user, String date, String status, int index, int pagesize) throws SQLException, NamingException {
        List<RequestDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "with x	as	(SELECT ROW_NUMBER() over (order by r.datebook ) as number,r.datebook, sr.statusreqname, u.name, res.itemname ,r.requestID,r.quantityReq,r.itemID  "
                        + "                        FROM tblRequest r, tblResource res, tblUsers u,tblStatusRequest sr "
                        + "                        WHERE r.itemID = res.itemID "
                        + "                           AND r.statusreqID=sr.statusreqID "
                        + "                             AND r.userID=u.userID " + user + date + status + search
                        + "                         GROUP BY r.datebook, sr.statusreqname, u.name, res.itemname,r.requestID,r.quantityReq,r.itemID  ) "
                        + "						 SELECT number,datebook, statusreqname, name, itemname ,requestID,quantityReq,itemID  "
                        + "						 FROM X "
                        + "						 WHERE number between ?*?-? AND ?*? "
                        + "						 ORDER BY datebook ASC";
                stm = con.prepareStatement(sql);
                stm.setInt(1, index);
                stm.setInt(2, pagesize);
                stm.setInt(3, pagesize - 1);
                stm.setInt(4, index);
                stm.setInt(5, pagesize);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date datebook = rs.getDate("datebook");
                    int quantityReq = rs.getInt("quantityReq");
                    String statusreqID = rs.getString("statusreqname");
                    String userID = rs.getString("name");
                    //String itemID = rs.getString("itemname");
                    ResourceDTO res=new ResourceDTO(rs.getString("itemID"), rs.getString("itemname"), "", "", 0, datebook);
                    RequestDTO dto = new RequestDTO(requestID, datebook, quantityReq, statusreqID, userID, res);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertRequest(RequestDTO req,ResourceDTO res) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblRequest (datebook,quantityReq,statusreqID,userID,itemID) "
                        + "VALUES (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setDate(1, req.getDatebook());
                stm.setInt(2, req.getQuantityReq());
                stm.setString(3, req.getStatusreqID());
                stm.setString(4, req.getUserID());
                stm.setString(5, res.getItemID());
                check = stm.executeUpdate() == 0 ? false : true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteRequest(String statusID,int requestID) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblRequest "
                        + "SET statusreqID= ? "
                        + "WHERE requestID = ? ";
                stm=con.prepareStatement(sql);
                stm.setString(1, statusID);
                stm.setInt(2, requestID);
                check=stm.executeUpdate()==0?false:true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean acceptRequest(int requestID) throws SQLException, NamingException{
        boolean check=false;
        try{
            con=DBUtils.makeConnection();
            if (con!=null){
                String sql="UPDATE tblRequest "
                        + "SET statusreqID='sr2' "
                        + "WHERE requestID = ? ";
                stm=con.prepareStatement(sql);
                stm.setInt(1, requestID);
                check=stm.executeUpdate()==0?false:true;
            }
        }finally{
            closeConnection();
        }
        return check;
    }
}
