/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.resources;

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
public class ResourceDAO implements Serializable {

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

    public int countResource(String searchvalue, String date_sql, String category_sql) throws SQLException, NamingException {
        int count = 0;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "	SELECT Count (itemID) as count"
                        + "   FROM tblResource r, tblCategory c "
                        + "    WHERE c.categoryID=r.categoryID AND r.itemname LIKE ? " + date_sql + category_sql;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchvalue + "%");
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

    public List<ResourceDTO> getlistResource(String searchvalue, int index, int size, String date, String category_sql) throws SQLException, NamingException {
        List<ResourceDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "with x as "
                        + "	(SELECT ROW_NUMBER() over (order by itemID asc)as number,itemID, itemname, color, c.categoryname, quantity, r.dateresoure "
                        + "                        FROM tblResource r, tblCategory c "
                        + "                        WHERE c.categoryID=r.categoryID AND r.itemname Like ? " + category_sql + date
                        + "                         GROUP BY itemID,itemname, color, c.categoryname, quantity, r.dateresoure)"
                        + "						 SELECT number,itemID, itemname, color, categoryname, quantity, dateresoure"
                        + "						 FROM x"
                        + "						 WHERE number between ?*?-? and ?*?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchvalue + "%");
                stm.setInt(2, index);
                stm.setInt(3, size);
                stm.setInt(4, size - 1);
                stm.setInt(5, index);
                stm.setInt(6, size);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String itemID = rs.getString("itemID");
                    String itemname = rs.getString("itemname");
                    String color = rs.getString("color");
                    String categoryname = rs.getString("categoryname");
                    int quantity = rs.getInt("quantity");
                    Date dateresoure = rs.getDate("dateresoure");
                    ResourceDTO src = new ResourceDTO(itemID, itemname, color, categoryname, quantity, dateresoure);
                    list.add(src);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int getQuantityResource(String itemID) throws SQLException, NamingException {
        int count=0;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT res.quantity  "
                        + " FROM tblResource res "
                        + " WHERE res.itemID = ? ";
                stm=con.prepareStatement(sql);
                stm.setString(1, itemID);
                rs=stm.executeQuery();
                if (rs.next()){
                    count=rs.getInt("quantity");                    
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public boolean updateResource(int quantity_request, String itemID,int quantity_resource) throws SQLException, NamingException {
        boolean check = false;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblResource "
                        + " SET quantity= ? - ? "
                        + " WHERE itemID = ? AND ? - ? >= 0 ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity_resource);
                stm.setInt(2, quantity_request);
                stm.setString(3, itemID);
                stm.setInt(4, quantity_resource);
                stm.setInt(5, quantity_request);
                check = stm.executeUpdate() == 0 ? false : true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
}
