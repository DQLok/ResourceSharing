/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locdq.requests.RequestDAO;
import locdq.requests.RequestDTO;
import locdq.resources.ResourceDAO;
import locdq.resources.ResourceDTO;

/**
 *
 * @author test
 */
public class RequestServlet extends HttpServlet {

    private final String ERROR = "SearchServlet";
    private final String SUCCESS = "SearchServlet";

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
        String itemID = request.getParameter("itemID");
        try {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int tmp = Integer.parseInt(request.getParameter("tmp"));
            String userID = request.getParameter("userID");            
            String itemname = request.getParameter("itemname");
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            String statusreqID = "sr1";
            //------------
            ResourceDAO daores = new ResourceDAO();
            int up_quantity = 0;
            int quantity_resource = daores.getQuantityResource(itemID);
            if (tmp == quantity && tmp != 0) {
                up_quantity = 1;
            }
            if (tmp < quantity && tmp > 0) {
                up_quantity = tmp;
            }
            if (tmp == quantity && tmp == 0 || tmp>quantity || tmp < 0) {
                request.setAttribute("RESID", itemID);
                request.setAttribute("ERROR", "Resource is over!!!");
            }
            request.setAttribute("QUANTITY", quantity_resource-up_quantity);
            if (up_quantity != 0 && quantity_resource >= up_quantity) {
                RequestDAO dao = new RequestDAO();
                ResourceDTO res = new ResourceDTO(itemID, itemname, "", "", quantity, date);
                RequestDTO req = new RequestDTO(0, date, up_quantity, statusreqID, userID, res);
                if (dao.insertRequest(req, res)) {
                    url = SUCCESS;
                }else{
                    request.setAttribute("ERROR", "Resource is over!!!");
                }
            }
        } catch (Exception e) {
            request.setAttribute("RESID", itemID);
            request.setAttribute("ERROR", "We need quantity resource integer");
            log("Error RequestServlet" + e.getStackTrace());
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
