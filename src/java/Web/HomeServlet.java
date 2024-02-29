/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Model.Category;
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

/**
 *
 * @author 20t10
 */

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategory = Database.getCategorydao().findAll();
        request.setAttribute("listCategory", listCategory);
        List<Product> listProduct = Database.getProductdao().findAll();
        request.setAttribute("listProduct", listProduct);
        
        request.setAttribute("id_category", request.getParameter("id_category"));
   
        addProductToCart(request);
        request.setAttribute("title", "Home Page");
        request.getRequestDispatcher("./views/home.jsp").include(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    void addProductToCart(HttpServletRequest request) {
    String idProductParam = request.getParameter("id_product");
    if (idProductParam != null && !idProductParam.isEmpty()) {
        int id_product;
        try {
            id_product = Integer.parseInt(idProductParam);
        } catch (NumberFormatException e) {
            // Xử lý nếu không thể chuyển đổi thành số nguyên
            e.printStackTrace();
            return; // Kết thúc hàm nếu có lỗi chuyển đổi
        }

        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        if (id_product > 0) {
            Product product = Database.getProductdao().findProduct(id_product);
            boolean isProductInCart = false;
            for (Product pro : cart)
                if (pro.getId() == id_product) {
                    pro.setQuantity(pro.getQuantity() + 1);
                    isProductInCart = true;
                }
            if (!isProductInCart) {
        cart.add(product);
        System.out.println("Added product to cart: " + product.getName());
}
        }
        request.getSession().setAttribute("cart", cart);
    }
}
}
