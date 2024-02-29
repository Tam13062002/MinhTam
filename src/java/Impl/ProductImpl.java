/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.ProductDao;
import Driver.MySqlDriver;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductImpl implements ProductDao {

    Connection con = MySqlDriver.getConnection();

    @Override
    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);

            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("productID");
                int id_category = rs.getInt("categoryID");
                String name = rs.getString("productName");
                String image = rs.getString("photo");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, name, image, price, quantity, id_category, status));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    @Override
    public Product findProduct(int id_product) {
        String sql = "select *from products where productID=" + id_product;
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return new Product(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
