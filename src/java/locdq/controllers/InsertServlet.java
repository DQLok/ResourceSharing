/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;
import locdq.users.UsersErrorDTO;
import locdq.utils.SendGmail;

/**
 *
 * @author test
 */
public class InsertServlet extends HttpServlet {

    private final String ERROR = "insert.jsp";
    private final String SUCCESS = "verify.jsp";

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
        boolean check = true;
        UsersErrorDTO error = new UsersErrorDTO("", "", "", "", "", "");
        try {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            UsersDAO dao = new UsersDAO();
            if (!userID.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")) {
                check = false;
                error.setUserIDError("UserID ___@___.___ gmail address for you)");
            }
            if (password.length() < 0 || password.length() > 20) {
                check = false;
                error.setPasswordError("Password must be in [0,50]");
            }
            if (!confirm.equals(password)) {
                check = false;
                error.setConfirm("Confirm not match");
            }
            if (!phone.matches("^[0-9]{10}")) {
                check = false;
                error.setPhoneError("Phone has 10 numbers");
            }
            if (!name.matches("^[\\p{L} .'-]+$") || name.trim().length() < 2 || name.trim().length() > 30) {
                check = false;
                error.setNameError("Name must be in [2-30]");
            }
            if (address.trim().length() < 0 || address.trim().length() > 50) {
                check = false;
                error.setAddressError("Address must be in [0-50]");
            }
            if (dao.checkUserID(userID) != null) {
                check = false;
                error.setUserIDError("UserID exist!!!");
            }
            if (check) {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                String statusID = "s1";
                String role = "user";
                UsersDTO user = new UsersDTO(userID, password, phone, name, address, statusID, date, role);
                if (dao.insert(user)) {
                    Random id = new Random();
                    int id_verify = id.nextInt();
                    HttpSession session=request.getSession();
                    session.setAttribute("ID_VERIFY", id_verify);
                    SendGmail.sendHTML(userID,id_verify,user.getName());
                    session.setAttribute("NEWUSER", user);
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("UsersError", error);
            }
        } catch (Exception e) {
            log("Error InsertServlet"+e.getStackTrace());
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
