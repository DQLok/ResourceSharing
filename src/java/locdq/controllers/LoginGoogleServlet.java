/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;
import locdq.utils.GooglePojo;
import locdq.utils.GoogleUtils;

/**
 *
 * @author test
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {

    private final String ERROR = "LogoutServlet";
    private final String SUCCESS = "ResourceServlet";
    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

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
        String code = request.getParameter("code");
        String url = ERROR;
        try {
            if (code == null || code.isEmpty()) {
                url = ERROR;
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                request.setAttribute("id", googlePojo.getId());
                request.setAttribute("name", googlePojo.getName());
                request.setAttribute("email", googlePojo.getEmail());
                UsersDAO dao = new UsersDAO();                
                HttpSession session = request.getSession();

                if (dao.checkUserID(googlePojo.getEmail()) != null) {
                    UsersDTO user = dao.checkUserID(googlePojo.getEmail());
                    session.setAttribute("USER", user);
                    url = SUCCESS;
                } else {
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    UsersDTO newuser = new UsersDTO(googlePojo.getEmail(), "***", "***", googlePojo.getEmail(), googlePojo.getId(), "s1", date, "user");
                    if (dao.insert(newuser)){
                    session.setAttribute("USER", newuser);
                    url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error LoginGoogleServlet"+e.getStackTrace());
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
