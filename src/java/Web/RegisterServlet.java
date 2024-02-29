/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;


import Model.User;
import Dao.Database;
import utils.API;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setAttribute("title", "Register Page");
      
       request.getRequestDispatcher("./views/register.jsp").include(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String err_email="",err_phone="",err_repassword="";
      String name=request.getParameter("name");
      String email=request.getParameter("email");
      String phone=request.getParameter("phone");
      String password=request.getParameter("password");
      String repassword=request.getParameter("repassword");
      String Email_Regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
      String Phone_Regex="^\\d{10}$";
      boolean err=false;
      if(!email.matches(Email_Regex)){
          err_email="Email is Invalid";
          request.getSession().setAttribute("err_email", err_email);
          err=true;
      }else{
          err_email="";
          request.getSession().removeAttribute("err_email");
      }
       if(!phone.matches(Phone_Regex)){
          err_email="Phone is Invalid";
          request.getSession().setAttribute("err_phone", err_phone);
          err=true;
      }else{
          err_phone="";
          request.getSession().removeAttribute("err_phone");
      }
        if(!repassword.matches(password)){
          err_repassword="Password not match";
          request.getSession().setAttribute("err_repassword", err_repassword);
          err=true;
      }else{
          err_repassword="";
          request.getSession().removeAttribute("err_repassword");
      }
      if(err){
          response.sendRedirect("Register");
      }else{
          if(Database.getUserdao().findUser(email)!=null ||
                  Database.getUserdao().findUser(phone)!=null){
              request.getSession().setAttribute("exist_user", "User has existed in Database!");
               response.sendRedirect("Register");
               
      }else{
         
           Database.getUserdao().insertUser(name, email, phone,API.getMd5(repassword));
           User user=Database.getUserdao().findUser(email);
           request.getSession().setAttribute("user", user);
           request.getSession().removeAttribute("exist_user");
           response.sendRedirect("home");
         }
    }
}
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
