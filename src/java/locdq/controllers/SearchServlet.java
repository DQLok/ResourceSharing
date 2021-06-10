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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locdq.resources.ResourceDAO;
import locdq.resources.ResourceDTO;

/**
 *
 * @author test
 */
public class SearchServlet extends HttpServlet {

    private final String ERROR = "search.jsp";
    private final String SUCCESS = "search.jsp";
    private final int PAGE_SIZE = 20;

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
            HttpSession session = request.getSession();
            String searchvalue = request.getParameter("txtSearchValue");
            String category = request.getParameter("category");
            String dateresoure = request.getParameter("dateresoure");
            String index_string = request.getParameter("index");
            String category_tmp = "";        
            if (!category.equals("") && !category.equals("everything")) {
                category_tmp = " AND c.categoryID = '" + category + "' ";
            }
            String dateresoure_tmp = "";
            if (!dateresoure.equals("")) {
                dateresoure_tmp = " AND r.dateresoure = '" + dateresoure + "' ";
            }
            ResourceDAO dao = new ResourceDAO();
            int count = dao.countResource(searchvalue, dateresoure_tmp, category_tmp);
            int endpage = count / PAGE_SIZE;
            if (count % PAGE_SIZE != 0) {
                endpage++;
            }

            int index = 1;
            if (index_string == null || index_string.equals("") || Integer.parseInt(index_string) > count) {
                index = 1;
            } else {
                index = Integer.parseInt(index_string);
            }

            List<ResourceDTO> list = dao.getlistResource(searchvalue, index, PAGE_SIZE, dateresoure_tmp, category_tmp);
            if (list != null) {
                session.setAttribute("ENDPAGE", endpage);
                session.setAttribute("LISTRESOURCE", list);
                request.setAttribute("SEARCHVALUE", searchvalue);
                request.setAttribute("DATE", dateresoure);
                request.setAttribute("CATEGORY", category);
                request.setAttribute("INDEX", index);
                session.setAttribute("COUNTRESOURCE", count);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error SearchServlet" + e.getStackTrace());
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
