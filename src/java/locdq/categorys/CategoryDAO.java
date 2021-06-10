/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.categorys;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import locdq.utils.DBUtils;

/**
 *
 * @author test
 */
public class CategoryDAO implements Serializable {

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

    public List<CategoryDTO> getListCategory() throws SQLException, NamingException {
        List<CategoryDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT categoryname , categoryID "
                        + "FROM tblCategory ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryname = rs.getString("categoryname");
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryname);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
