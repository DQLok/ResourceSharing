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
import javax.servlet.http.HttpSession;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;
import locdq.utils.VerifyUtils;

/**
 *
 * @author test
 */
public class LoginServlet extends HttpServlet {

    private final String ERROR = "login.jsp";
    private final String SUCCESS = "ResourceServlet";
    //  private static final long serialVersionUID = 958900029856081978L;

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
        boolean valid = true;
        try {
            // lay param user vs password
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            //check user
            UsersDAO dao = new UsersDAO();
            UsersDTO user = dao.checklogin(userID, password);
            //check verify
            if (user.getStatusID().equals("s1")) {
                request.setAttribute("errorString", "You have not verified!!");
            }
            if (user != null && !user.getStatusID().equals("s1")) {
                HttpSession session = request.getSession();
                session.setAttribute("USER", user);
                //-------------
                String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
                valid = VerifyUtils.verify(gRecaptchaResponse);
                if (!valid) {
                    request.setAttribute("errorString", "Captcha invalid!!!");
                } else {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            request.setAttribute("errorString", "UserID or Password invalid!!!");
            log("Error LoginServlet" + e.getStackTrace());
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
