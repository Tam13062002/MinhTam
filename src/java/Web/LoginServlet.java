/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Model.User;
import Dao.Database;
import Dao.UserDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author 20t10
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setAttribute("title", "Login Page");
      
       request.getRequestDispatcher("./views/login.jsp").include(request, response);
    
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String emailphone = request.getParameter("emailPhone");
        String password = request.getParameter("password");
          User us= Database.getUserdao().findUser( emailphone, password);
       
        if (us==null) {
           request.getSession().setAttribute("erorr_login", "Your information is incorect!");
            response.sendRedirect("login");
        } else {
           if(us.getRole().equals("admin"))
            response.sendRedirect("admin");
           else{
           request.getSession().setAttribute("user", us);
           request.getSession().removeAttribute("erorr_login");
            response.sendRedirect("home");
           }
        }
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
