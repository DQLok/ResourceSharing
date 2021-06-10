/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locdq.requests.RequestDAO;
import locdq.resources.ResourceDAO;

/**
 *
 * @author test
 */
public class AcceptRequestServlet extends HttpServlet {

    private final String ERROR = "ProcessServlet";
    private final String SUCCESS = "ProcessServlet";

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
            // lay itemid va quantity request 
            String itemID = request.getParameter("itemID");
            int quantity_request = Integer.parseInt(request.getParameter("quantityreq"));
            String requestID = request.getParameter("requestID");
            RequestDAO dao = new RequestDAO();
            ResourceDAO daores = new ResourceDAO();
            
            int quanity_resource = daores.getQuantityResource(itemID);
            if (daores.updateResource(quantity_request, itemID, quanity_resource)) {
                if (dao.acceptRequest(Integer.parseInt(requestID))) {
                    url = SUCCESS;
                }
            }else {
                request.setAttribute("REQID", requestID);
                request.setAttribute("ERROR", "Resource is over!!!");
            }
        } catch (Exception e) {
            log("Error in AcceptServlet" + e.getStackTrace());
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
