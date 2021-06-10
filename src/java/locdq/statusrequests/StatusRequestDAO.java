/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.statusrequests;

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
public class StatusRequestDAO implements Serializable {

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

    public List<StatusRequestDTO> getListStatusRequest() throws SQLException, NamingException {
        List<StatusRequestDTO> list = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT statusreqID,statusreqname "
                        + "	 FROM tblStatusRequest ";
                stm=con.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    String statusreqID=rs.getString("statusreqID");
                    String statusreqname=rs.getString("statusreqname");
                    StatusRequestDTO dto=new StatusRequestDTO(statusreqID, statusreqname);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
