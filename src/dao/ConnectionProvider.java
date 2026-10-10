/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class ConnectionProvider {
    public static Connection getCon(){
        try {
            String url = System.getenv().getOrDefault("CAFE_DB_URL", "jdbc:mysql://localhost:3306/cms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh");
            String user = System.getenv().getOrDefault("CAFE_DB_USER", "root");
            String password = System.getenv().getOrDefault("CAFE_DB_PASSWORD", "");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
