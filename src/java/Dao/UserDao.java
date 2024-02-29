/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.User;

/**
 *
 * @author 20t10
 */
public interface UserDao {

   
    public User findUser(String username, String password);
    public User findUser(String username);
    public void insertUser(String name,String email,String phone, String password);
}

