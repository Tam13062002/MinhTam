/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.CategoryDao;
import Driver.MySqlDriver;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20t10
 */
public class CategoryImpl implements CategoryDao {
    Connection con= MySqlDriver.getConnection();
    @Override
    public List<Category> findAll() {
       List<Category> listCategory= new ArrayList<>();
       String sql="select * from categories";
        try {
            PreparedStatement sttm= con.prepareStatement(sql);
            
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                int id=rs.getInt("categoryID");
                String name=rs.getString("categoryName");
                listCategory.add(new Category(id,name));
            }
            
         } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }

   
}

