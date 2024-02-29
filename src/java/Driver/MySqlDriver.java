/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Constants;

/**
 *
 * @author 20t10
 */
public class MySqlDriver {
   public static Connection getConnection(){
            try {
               Class.forName("com.mysql.jdbc.Driver");
                try {
                    return DriverManager.getConnection(Constants.DB_URL,Constants.UER,Constants.PASS);
                } catch (SQLException ex) {
                    Logger.getLogger(MySqlDriver.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySqlDriver.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
}