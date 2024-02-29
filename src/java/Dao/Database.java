/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Impl.CategoryImpl;
import Impl.ProductImpl;
import Impl.UserImpl;

/**
 *
 * @author 20t10
 */
public class Database {
     public static CategoryDao getCategorydao(){
        return new CategoryImpl();
    }
     public static ProductDao getProductdao(){
        return new ProductImpl();
    }
      public static UserDao getUserdao(){
        return new UserImpl();
    }
}