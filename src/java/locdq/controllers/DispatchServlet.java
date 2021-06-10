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

/**
 *
 * @author test
 */
public class DispatchServlet extends HttpServlet {

    private final String ERROR = "login.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String INSERT_CONTROLLER = "InsertServlet";
    private final String REQUEST_CONTROLLER = "RequestServlet";
    private final String VERIFY_CONTROLLER = "VerifyServlet";
    private final String PROCESS_CONTROLLER = "ProcessServlet";
    private final String DELETE_CONTROLLER = "DeleteRequestServlet";
    private final String ACCEPT_CONTROLLER = "AcceptRequestServlet";

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
            String button = request.getParameter("btAction");
            if (button == null) {
                
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Insert")) {
                url = INSERT_CONTROLLER;
            } else if (button.equals("Booking")) {
                url = REQUEST_CONTROLLER;
            } else if (button.equals("CheckVerify")) {
                url = VERIFY_CONTROLLER;
            } else if (button.equals("SearchRequest")) {
                url = PROCESS_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_CONTROLLER;
            } else if (button.equals("Accept")) {
                url = ACCEPT_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error DispatchServlet" + e.getStackTrace());
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
