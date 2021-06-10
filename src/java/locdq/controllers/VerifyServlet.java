/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.users.UsersDAO;
import locdq.users.UsersDTO;

/**
 *
 * @author test
 */
@WebServlet(name = "VerifyServlet", urlPatterns = {"/VerifyServlet"})
public class VerifyServlet extends HttpServlet {
    private final String ERROR="verify.jsp";
    private final String SUCCESS="verify.jsp";
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
        String url=ERROR;
        try {
            String CodeVerify=request.getParameter("CodeVerify");
            HttpSession session=request.getSession();
            //int code=Integer.parseInt(CodeVerify);
            int id_verify=(Integer)session.getAttribute("ID_VERIFY");
            if (id_verify == Integer.parseInt(CodeVerify)){
                request.setAttribute("REPORT_VERIFY", "Code matching !!! You are verify email successfully");
                UsersDTO user=(UsersDTO)session.getAttribute("NEWUSER");
                UsersDAO dao=new UsersDAO();
                if (dao.AcceptUser(user.getUserID())){
                    session.removeAttribute("ID_VERIFY");
                    url=SUCCESS;
                }
            }
            if (id_verify != Integer.parseInt(CodeVerify)){
                request.setAttribute("REPORT_VERIFY_ERROR","Code Error!!!");
            }
        }catch(Exception e){
            request.setAttribute("REPORT_VERIFY_ERROR", "Code is Integer!!!");
            log("Error VerifyServlet"+e.getStackTrace());
        }finally{
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
