/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.requests.RequestDAO;
import locdq.requests.RequestDTO;
import locdq.statusrequests.StatusRequestDAO;
import locdq.statusrequests.StatusRequestDTO;

/**
 *
 * @author test
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

    private final String ERROR = "LogoutServlet";
    private final String SUCCESS = "profile.jsp";
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
        PrintWriter out = response.getWriter();
        String url = ERROR;
        try {
            String daterequest = request.getParameter("daterequest");
            String status = request.getParameter("status");
            String userID = request.getParameter("userID");
            String query = " AND r.userID = '" + userID + "' AND r.statusreqID <> 'sr3' ";
            HttpSession session = request.getSession();
            RequestDAO dao = new RequestDAO();
            int index = 1;
            int count = dao.countRequest("", "", "", query);
            int endpage = count / PAGESIZE;
            if (count % PAGESIZE != 0) {
                endpage++;
            }
            List<RequestDTO> list = dao.getListRequest("", query, "", "", index, PAGESIZE);
            StatusRequestDAO daosr = new StatusRequestDAO();
            List<StatusRequestDTO> listsr = daosr.getListStatusRequest();
            session.setAttribute("STATUSREQUEST", listsr);
            if (list != null) {
                session.setAttribute("ENDPAGE_REQUEST", endpage);
                session.setAttribute("LIST_REQUEST", list);
                request.setAttribute("STATUS", status);
                request.setAttribute("DATEREQUEST", daterequest);
                session.setAttribute("TOTAL", count);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Eror in ProfileServlet"+e.getStackTrace());
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
