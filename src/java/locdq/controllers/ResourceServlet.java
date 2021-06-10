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
import locdq.categorys.CategoryDAO;
import locdq.categorys.CategoryDTO;
import locdq.resources.ResourceDAO;
import locdq.resources.ResourceDTO;

/**
 *
 * @author test
 */
public class ResourceServlet extends HttpServlet {
    private final String ERROR="LogoutServlet";
    private final String SUCCESS="search.jsp";
    private final int PAGE_SIZE=20;
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
            int index=1;
            HttpSession session = request.getSession();
            ResourceDAO dao = new ResourceDAO();
            int count=dao.countResource("","","");
            int endpage=count/PAGE_SIZE;
            if (count%PAGE_SIZE !=0){
                endpage++;
            }
            List<ResourceDTO> list = dao.getlistResource("",index,PAGE_SIZE,"","");
            session.setAttribute("LISTRESOURCE", list);            
            session.setAttribute("ENDPAGE", endpage);
            session.setAttribute("COUNTRESOURCE", count);
            
            CategoryDAO daoca = new CategoryDAO();
            List<CategoryDTO> listca = daoca.getListCategory();
            session.setAttribute("LISTCATEGORY", listca);
            
            url = SUCCESS;
        } catch (Exception e) {
            log("Error UserServlet"+e.getStackTrace());
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
