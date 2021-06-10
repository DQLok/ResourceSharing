/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.requests.RequestDAO;
import locdq.requests.RequestDTO;

/**
 *
 * @author test
 */
public class ProcessServlet extends HttpServlet {

    private final String ERROR = "LogoutServlet";
    private final String SUCCESS_ADMIN = "admin.jsp";
    private final String SUCCESS_USER = "profile.jsp";
    private final int PAGESIZE = 20;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            String role = request.getParameter("role");
            String daterequest = request.getParameter("daterequest");
            String status = request.getParameter("status");
            String index_string = request.getParameter("indexr");
            String Searchvalue = request.getParameter("Searchvalue");
            //--------- lay cau query---
            String query_date = "";
            if (!daterequest.equals("")) {
                query_date = " AND r.datebook = '" + daterequest + "' ";
            }
            String query_status = "";
            if (!status.equals("") && !status.equals("everything")) {
                query_status = " AND sr.statusreqID= '" + status + "' ";
            }

            HttpSession session = request.getSession();
            RequestDAO dao = new RequestDAO();
            int count = 0;
            String query_user = "";
            String query_search = "";
            String query_admin="";            
            if (role.equals("user")) {
                //---count user
                if (!Searchvalue.equals("") && Searchvalue != null) {
                    query_search = " AND res.itemname LIKE '%" + Searchvalue + "%' ";
                }
                String userID = request.getParameter("userID");
                query_user = " AND r.userID = '" + userID + "' AND r.statusreqID <> 'sr3' ";
                count = dao.countRequest(query_search, query_status, query_date, query_user);
            } else {
                //--- count admin
                query_admin=" AND r.statusreqID <> 'sr4' ";
                count = dao.countRequest(query_admin, query_status, query_date, "");
            }
            // phan trang
            int endpage = count / PAGESIZE;
            if (count % PAGESIZE != 0) {
                endpage++;
            }
            int index = 1;
            if (index_string == null || index_string.equals("") || Integer.parseInt(index_string) > count) {
                index = 1;
            } else {
                index = Integer.parseInt(index_string);
            }
            //--- set atribute
            session.setAttribute("INDEXR", index);
            request.setAttribute("STATUS", status);
            request.setAttribute("DATEREQUEST", daterequest);
            if (role.equals("user")) {
                List<RequestDTO> list = dao.getListRequest(query_search, query_user, query_date, query_status, index, PAGESIZE);
                session.setAttribute("LIST_REQUEST", list);
                session.setAttribute("ENDPAGE_REQUEST", endpage);
                session.setAttribute("TOTAL", count);
                request.setAttribute("SEARCH", Searchvalue);
                url = SUCCESS_USER;
            }
            if (role.equals("admin")) {
                List<RequestDTO> list = dao.getListRequest(query_admin, "", query_date, query_status, index, PAGESIZE);
                session.setAttribute("LIST_REQUEST", list);
                session.setAttribute("ENDPAGE_REQUEST", endpage);
                session.setAttribute("TOTAL", count);
                url = SUCCESS_ADMIN;
            }
        } catch (Exception e) {
            log("Error in ProcessServlet" + e.getStackTrace());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
