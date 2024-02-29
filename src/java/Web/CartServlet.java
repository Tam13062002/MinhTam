/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Model.Product;
import Dao.Database;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Cart Detail");

        request.getRequestDispatcher("./views/cart.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        updateDelete(request, response);
    }

    void updateDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id_product"));
        switch (action) {
            case "delete":
                doDelete(request, id);
                break;
            case "update":
                doUpdate(request, id);
                break;
          
        }
        response.sendRedirect("cart");   
}
    void doUpdate(HttpServletRequest request, int id_product) {
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        for (Product product : cart) {
            if (product.getId() == id_product && quantity > 0) {
                product.setQuantity(quantity);
                break;
            }
        }
        request.getSession().setAttribute("cart", cart);
    }

    void doDelete(HttpServletRequest request, int id_product) {
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");

        for (Product product : cart) {
            if (product.getId() == id_product) {
                cart.remove(product); break;
            }
        }
        request.getSession().setAttribute("cart", cart);
    }
    
}
